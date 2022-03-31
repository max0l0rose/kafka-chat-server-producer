package com.shubh.kafkachat.controller;

import com.shubh.kafkachat.constants.KafkaConstants;
import com.shubh.kafkachat.model.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class ChatController {

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    @PostMapping(value = "/send", consumes = "application/json", produces = "application/json")
    public void sendMessage(@RequestBody Message message)
    {
        message.setTimestamp(LocalDateTime.now().toString());
        try {
            kafkaTemplate.send(KafkaConstants.KAFKA_TOPIC, message).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @AllArgsConstructor
    @Data
    static class Item {
        int key;
        String name;
    }

    @GetMapping(value = "/list1")
    @CrossOrigin(origins = "http://localhost:3000") // can be on controller
    public List<Item> list1()
    {
        return new ArrayList<Item>() {{
            add(new Item(1, "qqqq"));
            add(new Item(2, "wwwww"));
            add(new Item(3, "eeeeeee"));
        }};
        //return List.of("qqqq", "wwwww");
    }


//    //    -------------- WebSocket API ----------------
//    @MessageMapping("/sendMessage")
//    @SendTo("/topic/group")
//    public Message broadcastGroupMessage(@Payload Message message) {
//        //Sending this message to all the subscribers
//        return message;
//    }
//
//    @MessageMapping("/newUser")
//    @SendTo("/topic/group")
//    public Message addUser(@Payload Message message,
//                           SimpMessageHeaderAccessor headerAccessor) {
//        // Add user in web socket session
//        headerAccessor.getSessionAttributes().put("username", message.getSender());
//        return message;
//    }

}
