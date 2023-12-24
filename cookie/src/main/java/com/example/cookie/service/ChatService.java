package com.example.cookie.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.example.cookie.model.chat.ChatRoom;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class ChatService {

	private final ObjectMapper objectMapper;
	private Map<String, ChatRoom> chatRooms; // DB연동되면 방을 DB에저장하고 db 없을때는 Map형태로 방을 저장
	
	@PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRoom() {
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoom findRoomById(String roomId) {
        return chatRooms.get(roomId);
    }

    public ChatRoom createRoom(ChatRoom room) {
        ChatRoom chatRoom = ChatRoom.builder()
        		.roomId(room.getRoomId())
        		.roomName(room.getRoomName())
        		.host_name(room.getHost_name())
        		.build();
        chatRooms.put(room.getRoomId(), chatRoom);
        return chatRoom;
    }
}
