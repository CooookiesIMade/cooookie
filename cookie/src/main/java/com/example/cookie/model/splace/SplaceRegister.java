package com.example.cookie.model.splace;

import lombok.Data;

@Data
public class SplaceRegister {

	private Long place_id;
	private String place_name;
	private String place_category;
	private String member_id;
	private Long place_count;
	private String place_address;
	private String place_content;
	private String place_profile;
	
	
	public static Splace toPlace(SplaceRegister splaceRegister) {
		Splace splace = new Splace();
		
		splace.setPlace_id(splaceRegister.getPlace_id());
		splace.setPlace_name(splaceRegister.getPlace_name());
		splace.setPlace_category(splaceRegister.getPlace_category());
		splace.setPlace_count(splaceRegister.getPlace_count());
		splace.setMember_id(splaceRegister.getMember_id());
		splace.setPlace_address(splaceRegister.getPlace_address());
		splace.setPlace_content(splaceRegister.getPlace_content());
		splace.setPlace_profile(splaceRegister.getPlace_profile());
		
		return splace;
	}

}
