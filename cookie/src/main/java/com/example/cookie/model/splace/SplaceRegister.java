package com.example.cookie.model.splace;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SplaceRegister {

	@NotBlank(message="제목을 입력하세요")
	private String place_name;
	@NotBlank(message="주소를 입력하세요")
	private String place_address;
	private String place_profile;
	private String place_content;
	private Long place_price;
}
