package com.example.cookie.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.cookie.model.chat.ChatRoom;
import com.example.cookie.model.member.Member;
import com.example.cookie.service.ChatService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {
	private final ChatService chatService;
	
	@RequestMapping("/chat/chatList")
	public String chatList(Model model) {
		List<ChatRoom> roomList = chatService.findAllRoom();
		model.addAttribute("roomList", roomList);
		
		return "chat/chatList";
	}
	
	@PostMapping("/chat/createRoom")
	public String createRoom(Model model, @ModelAttribute("data")ChatRoom chatRoom) {
		ChatRoom room = chatService.createRoom(chatRoom);
		model.addAttribute("room", room);
		log.info("data : {}",chatRoom);
		return "chat/chatRoom"; // 만든사람이 채팅방 1번으로 들어가게 됨
	}
	
	@GetMapping("/chat/chatRoom")
	public String chatRoom(Model model, @SessionAttribute("signInMember") Member signinMember, 
			@RequestParam String roomId) {
		ChatRoom room = chatService.findRoomById(roomId);
		room.setSender(signinMember.getMember_nick());
		log.info("보내는 사람 : {} ", room.getSender());
		model.addAttribute("room", room);
		return "chat/chatRoom";
	}
	
}
