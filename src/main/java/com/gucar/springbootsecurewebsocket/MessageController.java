package com.gucar.springbootsecurewebsocket;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessageController {

  private final SimpMessagingTemplate simpMessagingTemplate;

  // Mapped as /app/application
  @MessageMapping("/application")
  @SendTo("/common/messages")
  public Message send(final Message message) {
    return message;
  }

  // Mapped as /app/private
  @MessageMapping("/private")
  public void sendToSpecificUser(@Payload Message message) {
    simpMessagingTemplate.convertAndSendToUser(message.getTo(), "/specific", message);
  }
}
