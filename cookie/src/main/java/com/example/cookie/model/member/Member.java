package com.example.cookie.model.member;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Member {

	private String member_id;
	private String member_pw;
	private String member_nick;
	private String member_pho;
	private String member_mbti;
	private LocalDateTime member_new;
	private String member_profile;
	
	// 이전 비밀번호를 저장할 필드
    private String previousPassword;
	
}
