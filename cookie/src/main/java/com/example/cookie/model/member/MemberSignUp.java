package com.example.cookie.model.member;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class MemberSignUp {

	@NotBlank(message = "아이디를 입력하세요")
	@Email(message = "이메일 주소를 정확히 입력해 주세요")
	private String member_id;
	@NotBlank(message = "비밀번호를 입력하세요")
	@Length(min = 4, max = 20, message = "비밀번호는 4~20자 사이로 입력해주세요")
	private String member_pw;
	@NotBlank(message = "닉네임을 입력해 주세요")
	private String member_nick;
	@NotBlank(message = "연락처를 입력해주세요")
	private String member_pho;
	@NotBlank(message = "MBTI를 입력해 주세요")
	private String member_mbti;
	private String saved_filename;
	
	
	public static Member toMember(MemberSignUp memberSignUp) {
		Member member = new Member();
		
		member.setMember_id(memberSignUp.getMember_id());
		member.setMember_pw(memberSignUp.getMember_pw());
		member.setMember_nick(memberSignUp.getMember_nick());
		member.setMember_pho(memberSignUp.getMember_pho());	
		member.setMember_mbti(memberSignUp.getMember_mbti());
		member.setSaved_filename(memberSignUp.getSaved_filename());
		
		return member;
	}
}
