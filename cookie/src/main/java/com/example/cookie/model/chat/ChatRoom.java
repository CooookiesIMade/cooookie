package com.example.cookie.model.chat;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.WebSocketSession;

import lombok.Builder;
import lombok.Data;

@Data
public class ChatRoom {

	private String roomId;
	private String name;
	// 방 한개마다 여러사용자들을 set형태로 가지고 있게
	private Set<WebSocketSession> sessions = new HashSet<>();
	
	@Builder
	public ChatRoom(String roomId, String name) {
		this.roomId = roomId;
		this.name = name;
	}
}