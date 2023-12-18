package com.example.cookie.model.rent;

import java.util.Date;

import lombok.Data;

@Data
public class RentPlaceRegister {

	private Long rentPlace_id;
	private String member_id;
	private Long place_id;
	private Date rentPlace_start;
	private Date rentPlace_end;
	private Long rentPlace_count;
	private Long rentPlace_price;
	
	public void toRentPlace(RentPlaceRegister rentPlaceRegister) {
		this.rentPlace_id = rentPlaceRegister.getRentPlace_id();
		this.member_id = rentPlaceRegister.getMember_id();
		this.place_id = rentPlaceRegister.getPlace_id();
		this.rentPlace_start = rentPlaceRegister.getRentPlace_start();
		this.rentPlace_end = rentPlaceRegister.getRentPlace_end();
		this.rentPlace_count = rentPlaceRegister.getRentPlace_count();
		this.rentPlace_price = rentPlaceRegister.getRentPlace_price();
	}
}
