package com.example.cookie.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.cookie.model.file.AttachedFile;
import com.example.cookie.model.member.Member;
import com.example.cookie.model.rent.RentPerson;
import com.example.cookie.model.rent.RentPlace;
import com.example.cookie.model.sperson.SPerson;
import com.example.cookie.model.splace.Splace;

@Mapper
public interface MemberMapper {

	void saveMember(Member member);
	
	Member findMember(String member_id);
	
	void updateMember(Member member);
	
	void updateAdminMember(Member mbember);
	
	List<RentPlace> findRentPlaces(String member_id);
	
	List<RentPerson> findRentPerson(String member_id);
	
	void saveFile(AttachedFile attachedFile);

	List<Splace> findRegisterPlaces(String member_id);

	List<SPerson> findRegisterPerson(String member_id);

}
