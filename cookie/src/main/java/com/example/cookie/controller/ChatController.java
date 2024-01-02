package com.example.cookie.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.cookie.model.chat.ChatRoom;
import com.example.cookie.model.splace.Splace;
import com.example.cookie.service.ChatService;
import com.example.cookie.service.PlaceService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {
    private final ChatService chatService;
    private final PlaceService placeService;


    @RequestMapping("/chat/chatList")
    public String chatList(Model model,  @RequestParam Long roomId){
        List<ChatRoom> roomList = chatService.findAllRoom();
        model.addAttribute("roomList",roomList);
        model.addAttribute("roomId", roomId);
        return "chat/chatList";
    }


    @PostMapping("/chat/createRoom")  //방을 만들었으면 해당 방으로 가야지.
    public String createRoom(Model model, @ModelAttribute("data")ChatRoom chatRoom ) {
        ChatRoom room = chatService.createRoom(chatRoom.getName(), chatRoom.getRoomId());
        model.addAttribute("room",room);
        return "chat/chatRoom";  //만든사람이 채팅방 1빠로 들어가게 됩니다
    }

    @GetMapping("/chat/chatRoom")
    public String chatRoom(Model model, @RequestParam Long roomId){
        ChatRoom room = chatService.findRoomById(roomId);
        Splace place = placeService.findPlaceById(roomId);
        model.addAttribute("room",room); 
        model.addAttribute("place", place);
        return "chat/chatRoom";
    }
}