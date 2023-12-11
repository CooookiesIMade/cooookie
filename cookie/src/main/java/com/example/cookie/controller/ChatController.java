package com.example.cookie.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.cookie.model.chat.ChatRoom;
import com.example.cookie.service.ChatService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChatController {
	private final ChatService chatService;
	
	@RequestMapping("/chat/chatList")
	public String chatList(Model model) {
		List<ChatRoom> roomList = chatService.findAllRoom();
		model.addAttribute("roomList", roomList);
		
		return "chat/chatList";
	}
	
	@PostMapping("/chat/createRoom")
	public String createRoom(Model model, @RequestParam String name, String username) {
		ChatRoom room = chatService.createRoom(name);
		model.addAttribute("room", room);
		model.addAttribute("username", username);
	
		return "chat/chatRoom"; // 만든사람이 채팅방 1번으로 들어가게 됨
	}
	
	@GetMapping("/chat/chatRoom")
	public String chatRoom(Model model, @RequestParam String roomId) {
		ChatRoom room = chatService.findRoomById(roomId);
		model.addAttribute("room", room);
		
		return "chat/chatRoom";
	}
	
}
