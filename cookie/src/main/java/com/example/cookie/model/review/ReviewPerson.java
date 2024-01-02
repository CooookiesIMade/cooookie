package com.example.cookie.model.review;

import java.util.Date;

import lombok.Data;

@Data
public class ReviewPerson {

	private Long review_id;
	private String member_id;
	private Long person_id;
	private String review_contents;
	private Date review_time;
	private String person_name;
	private String saved_filename;
	private String member_nick;
}
