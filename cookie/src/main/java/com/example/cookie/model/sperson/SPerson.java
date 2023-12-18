package com.example.cookie.model.sperson;

import lombok.Data;

@Data
public class SPerson {

	private Long person_id;
	private String member_id;
	private String person_name;
	private String person_content;
	private String saved_filename;
	private String person_category;
	private String member_nick;
}
