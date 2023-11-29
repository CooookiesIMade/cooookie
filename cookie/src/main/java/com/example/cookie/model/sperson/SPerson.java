package com.example.cookie.model.sperson;

import java.util.Date;

import lombok.Data;

@Data
public class SPerson {

	private Long person_id;
	private String member_id;
	private String person_name;
	private String person_content;
	private String person_profile;
	private Date person_start; //예약시작일
	private Date person_end; //예약종료일
	private Long person_price;
	
}
