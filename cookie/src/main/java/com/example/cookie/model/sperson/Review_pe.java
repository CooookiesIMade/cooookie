package com.example.cookie.model.sperson;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Review_pe {

	private Long review_id;
	private String member_id;
	private Long person_id;
	private String review_contents;
	private LocalDateTime review_time;
}
