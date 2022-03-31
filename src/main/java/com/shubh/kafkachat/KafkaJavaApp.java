package com.shubh.kafkachat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaJavaApp {

//    @Configuration
//    @EnableWebMvc
//    public class WebConfig implements WebMvcConfigurer {
//
//        @Override
//        public void addCorsMappings(CorsRegistry registry) {
//            registry.addMapping("/**");
//        }
//    }

    public static void main(String[] args) {
        SpringApplication.run(KafkaJavaApp.class, args);
    }
}
