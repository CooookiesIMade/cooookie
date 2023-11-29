package com.example.cookie.model.splace;

import java.time.LocalDateTime;

import lombok.Data;


@Data
public class Review_pl {

	private Long review_id;
	private String member_id;
	private Long place_id;
	private String review_contents;
	private LocalDateTime review_time;
}
