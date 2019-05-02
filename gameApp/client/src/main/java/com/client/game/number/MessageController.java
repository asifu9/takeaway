package com.client.game.number;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.client.game.dto.AuditDTO;

/**
 * Message controller class to push data to UI through web socket
 *
 */
@Controller
public class MessageController {

	@MessageMapping("/game.sendMessage")
	@SendTo("/game/public")
	public AuditDTO sendMessage(@Payload AuditDTO chatMessage) {

		return chatMessage;
	}

}