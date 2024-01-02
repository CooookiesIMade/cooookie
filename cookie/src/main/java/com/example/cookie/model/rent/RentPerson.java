package com.example.cookie.model.rent;

import java.util.Date;

import lombok.Data;

@Data
public class RentPerson {

	private Long rentPerson_id;
	private String member_id;
	private Long person_id;
	private String person_name;
	private Date rentPerson_time;
	private String saved_filename;
	private String member_mbti;
	private String member_nick;
	private String person_category;
	private String person_content;
}
