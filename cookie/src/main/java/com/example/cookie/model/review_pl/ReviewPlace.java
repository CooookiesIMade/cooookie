package com.example.cookie.model.review_pl;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ReviewPlace {

	private Long review_id;
	private String member_id;
	private Long place_id;
	private String review_contents;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
	private LocalDateTime review_time;
	
}