package com.example.cookie.model.splace;


import lombok.Data;

@Data
public class Splace {
	
	private Long place_id;
	private String member_id;
	private String place_name;
	private String place_category;
	private Long place_count;
	private String place_address;
	private String place_content;
	private Long place_price;
	private String saved_filename;
}