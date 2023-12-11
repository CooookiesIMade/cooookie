package com.example.cookie.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.cookie.model.member.Member;
import com.example.cookie.model.splace.Splace;

@Mapper
public interface SplaceMapper {

	int saveSplace(Splace splace);
	
	Splace findSplace(Long place_id);
	
	void updateSplace(Splace splace);
	
	void updateAdminSplace(Splace splace);
	

}
