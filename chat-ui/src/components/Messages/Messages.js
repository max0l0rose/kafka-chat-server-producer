import React from 'react'

const Messages = ({ messages, currentUser }) => {

    let renderMessage = (message) => {
        let { sender, content, color } = message;
        //console.log('---> message: ' + JSON.stringify(message));
        if (message.sender === 'system')
            var className = 'Messages-message currentUser sysMessage';
        else {
            const messageFromMe = currentUser.username === message.sender;
            var className = messageFromMe ? "Messages-message currentUser" : "Messages-message";
        }
        return (
            <li className={className}>
                <span
                    className="avatar"
                    style={{ backgroundColor: color }}
                />
                <div className="Message-content">
                    <div className="username">
                        {sender}
                    </div>
                    <div className="text">{content}</div>
                </div>
            </li>
        );
    };

    return (
        <ul className="messages-list">
            {messages.map(msg => renderMessage(msg))}
        </ul>
    )
}


export default Messages