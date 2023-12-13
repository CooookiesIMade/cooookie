package com.example.cookie.handler;

import java.io.IOException;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.example.cookie.model.chat.ChatMessage;
import com.example.cookie.model.chat.ChatRoom;
import com.example.cookie.service.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class WebSockChatHandler extends TextWebSocketHandler{

	private final ObjectMapper objectMapper;
	private final ChatService chatService;
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		
	}
	
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String payload = message.getPayload();
		ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
		ChatRoom room = chatService.findRoomById(chatMessage.getRoomId());
		
		// 방에 있는 현재 사용자 한명이 WebsocketSession
		Set<WebSocketSession> sessions = room.getSessions();
		if(chatMessage.getType().equals(ChatMessage.MessageType.ENTER)) {
			// 사용자가 방에 입장하면 Enter메세지를 보내도록 함
			// 새로운사용자가 socket 연결한 것이랑은 다름 
			// socket연결은 이 메세지 보내기전에 이미 되어있는 상태 
			sessions.add(session);
			chatMessage.setMessage(chatMessage.getSender() + "님이 입장했습니다");
		} else if(chatMessage.getType().equals(ChatMessage.MessageType.QUIT)) {
			sessions.remove(session);
			chatMessage.setMessage(chatMessage.getSender() + "님이 퇴장했습니다");
			sendToEachSocket(sessions, new TextMessage(objectMapper.writeValueAsString(chatMessage)));
		} else {
			// 입장,퇴장아닐때는 클라이언트로 온 메세지 그대로 전달
			sendToEachSocket(sessions, message);
		}
	}
	private void sendToEachSocket(Set<WebSocketSession> sessions, TextMessage message) {
		sessions.parallelStream().forEach(roomSession -> {
			try {
				roomSession.sendMessage(message);
			} catch(IOException e) {
				throw new RuntimeException(e);
			}
		});
	}
	
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// js에서 session.close해서 연결 끊음. 그리고 이 메소드 실행 
		
	}
	
}
