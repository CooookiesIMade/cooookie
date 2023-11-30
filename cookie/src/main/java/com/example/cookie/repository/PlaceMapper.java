package com.example.cookie.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.cookie.model.splace.Splace;

@Mapper
public interface PlaceMapper {

	void saveSplace(Splace splace);
	
	Splace findSplace(String place_name);
	
	void updateSplace(Splace splace);
	
	void updateAdminSplace(Splace splace);
	

}