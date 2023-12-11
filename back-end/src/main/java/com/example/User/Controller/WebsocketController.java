package com.example.User.Controller;

import com.example.User.Entity.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class WebsocketController {

    @MessageMapping("/chats/messages")
    @SendTo("/topic/public")
    public Message sendMessage(@Payload Message message){
        return message;
    }

    //add user to the web socket
    @MessageMapping("/chats.AddUser")
    @SendTo("/topic/public")
    public Message addUser(@Payload Message message, SimpMessageHeaderAccessor headerAccessor){
        headerAccessor.getSessionAttributes().put("username",message.getSender());
        return message;
    }
}
