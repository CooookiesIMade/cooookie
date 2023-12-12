package com.example.cookie.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.cookie.model.sperson.AttachedFile;
import com.example.cookie.model.sperson.SPerson;

@Mapper
public interface PersonMapper {

	void savePerson(SPerson sPerson);

}
