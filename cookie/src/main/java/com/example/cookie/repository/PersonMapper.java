package com.example.cookie.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.cookie.model.file.PersonAttachedFile;
import com.example.cookie.model.sperson.SPerson;

@Mapper
public interface PersonMapper {

	void savePerson(SPerson sPerson);
	void saveFile(PersonAttachedFile attachedFile);
}
