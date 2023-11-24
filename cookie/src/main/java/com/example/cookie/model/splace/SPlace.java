package com.example.cookie.model.splace;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SPlace {
	
	private Long place_id;
	private String member_id;
	private String place_name;
	private String place_address;
	private String place_content;
	private LocalDateTime place_start;
	private LocalDateTime place_end;
	private Long place_count;
	private Long place_price;
}
