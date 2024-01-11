package com.example.cookie.model.wish;

import java.util.Date;

import lombok.Data;

@Data
public class WishPlace {

	private Long wishPlace_id;
	private Long place_id;
	private Date wishPlace_cr;
	private Date wishPlace_up;
	private Date wishPlace_de;
	private String member_id;
}
