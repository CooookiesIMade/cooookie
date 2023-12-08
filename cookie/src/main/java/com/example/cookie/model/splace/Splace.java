package com.example.cookie.model.splace;

import java.util.Date;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Splace {
	
	private Long place_id;
	private String member_id;
	private String place_name;
	private String place_cat;
	private String place_address;
	private String place_content;
	private Date place_start;
	private Date place_end;
	private int place_count;
	private int place_price;
	private String place_profile;
	public String place_latitude;
	public String place_longitude;
}
