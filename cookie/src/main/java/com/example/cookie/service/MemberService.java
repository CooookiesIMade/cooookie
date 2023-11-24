package com.example.cookie.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cookie.model.member.Member;
import com.example.cookie.repository.MemberMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MemberService {

	private final MemberMapper memberMapper;
	
	public void saveMember(Member member) {
		memberMapper.saveMember(member);
	}

	public Member findMember(String member_id) {
		Member findMember = memberMapper.findMember(member_id);
		return findMember;
	}
}
