package com.example.cookie.model.sperson;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SPersonRegister {

	@NotBlank(message="이름을 입력하세요")
	private String person_name;
	@NotBlank(message="상세정보를 입력하세요")
	private String person_content;
	private String person_profile;
	@NotBlank(message="가격을 입력하세요")
	private Long person_price;
}
