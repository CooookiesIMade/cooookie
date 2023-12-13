package com.example.cookie.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.cookie.model.splace.Splace;

@Mapper
public interface PlaceMapper {

	void savePlace(Splace place);
}
