package com.example.cookie.model.review_pl;


import java.util.Date;



import lombok.Data;

@Data
public class ReviewPlace {

	private Long review_id;
	private String member_id;
	private Long place_id;
	private String review_contents;
	private Date review_time;
	private String place_name;
	private String saved_filename;
	private String place_address;
	
	
}