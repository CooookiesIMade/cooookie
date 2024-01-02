package com.example.cookie.model.chat;

import lombok.Data;

@Data
public class ChatMessage {

	public enum MessageType {
		ENTER,TALK,QUIT // 입장,채팅,나감
	}
	private MessageType type; // 메세지 타입 
	private Long roomId; // 방번호
	private String sender; // 메시지 보낸사람
	private String message; // 메시지
}
