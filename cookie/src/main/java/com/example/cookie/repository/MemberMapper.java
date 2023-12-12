package com.example.cookie.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.cookie.model.member.Member;

@Mapper
public interface MemberMapper {

	void saveMember(Member member);
	
	Member findMember(String member_id);
	
	void updateMember(Member member);
	
	void updateAdminMember(Member member);
	
	// 사용자 정보 수정을 위한 메서드 추가
    void updateMemberInfo(Member updatedMember);
    
    void updateMemberInfoWithoutIdChange(Member updatedMember);
}
