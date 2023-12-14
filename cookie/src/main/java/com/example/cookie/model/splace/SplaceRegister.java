package com.example.cookie.model.splace;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SplaceRegister {

	private Long place_id;
	@NotEmpty(message = "장소명 등록해주셈")
	private String place_name;
	@NotNull(message = "카테고리를 선택해주셈")
	private String place_category;
	private String member_id;
	private Long place_count;
	private String place_address;
	@NotNull(message = "상세정보를 입력해주셈")
	private String place_content;
	private Long place_price;
	private String saved_filename;
	
	
	public static Splace toPlace(SplaceRegister splaceRegister) {
		Splace splace = new Splace();
		
		splace.setPlace_id(splaceRegister.getPlace_id());
		splace.setPlace_name(splaceRegister.getPlace_name());
		splace.setPlace_category(splaceRegister.getPlace_category());
		splace.setPlace_count(splaceRegister.getPlace_count());
		splace.setMember_id(splaceRegister.getMember_id());
		splace.setPlace_address(splaceRegister.getPlace_address());
		splace.setPlace_content(splaceRegister.getPlace_content());
		splace.setPlace_price(splaceRegister.getPlace_price());
		splace.setSaved_filename(splaceRegister.getSaved_filename());
		
		return splace;
	}

}
