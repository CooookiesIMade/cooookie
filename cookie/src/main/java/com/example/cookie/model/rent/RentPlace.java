package com.example.cookie.model.rent;

import java.util.Date;

import lombok.Data;

@Data
public class RentPlace {

	private Long rentPlace_id;
	private String member_id;
	private Long place_id;
	private String place_name;
	private String place_address;
	private Date rentPlace_start;
	private Date rentPlace_end;
	private Long rentPlace_count;
	private Long rentPlace_price;
	private String saved_filename;
}
