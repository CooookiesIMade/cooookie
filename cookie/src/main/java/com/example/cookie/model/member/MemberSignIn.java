package com.example.cookie.model.member;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class MemberSignIn {

	@NotBlank(message = "아이디를 입력하세요")
	@Email(message = "이메일 주소를 정확히 입력해 주세요")
	private String member_id;
	@NotBlank(message = "비밀번호를 입력하세요")
	private String member_pw;
}
