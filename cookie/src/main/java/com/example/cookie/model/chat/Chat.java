package com.example.cookie.model.chat;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Chat {

	private Long chat_id;
	private Long person_id;
	private Long place_id;
	private String chat_contents;
	private LocalDateTime chat_time;
}
