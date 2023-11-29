package com.example.cookie.model.chat;

import lombok.Data;

@Data
public class Message {

	private Long messeage_id;
	private Long chat_id;
	private String caht_contents;
	private String chat_sender_id;
	private String chat_receiver_id;
}
