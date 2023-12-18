package com.example.cookie.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.cookie.model.member.Member;
import com.example.cookie.model.rent.RentPlace;

@Mapper
public interface MemberMapper {

	void saveMember(Member member);
	
	Member findMember(String member_id);
	
	void updateMember(Member member);
	
	void updateAdminMember(Member mbember);
	
	List<RentPlace> findRentPlaces(String member_id);

}