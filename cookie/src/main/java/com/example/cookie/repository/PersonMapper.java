package com.example.cookie.repository;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;

import com.example.cookie.model.file.AttachedFile;
import com.example.cookie.model.file.PersonAttachedFile;
import com.example.cookie.model.sperson.SPerson;

@Mapper
public interface PersonMapper {

	void savePerson(SPerson sPerson);

	void saveFile(PersonAttachedFile attachedFile);

	List<SPerson> findPersons();

	AttachedFile findFileByPersonId(Long person_id);

	AttachedFile findFileByAttachedFileId(Long id);

	void removeAttachedFile(Long attachedFile_id);
}
