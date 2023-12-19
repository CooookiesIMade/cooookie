package com.example.cookie.repository;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.cookie.model.file.AttachedFile;
import com.example.cookie.model.file.PersonAttachedFile;
import com.example.cookie.model.rent.RentPerson;
import com.example.cookie.model.rent.RentPlaceRegister;
import com.example.cookie.model.sperson.SPerson;

@Mapper
public interface PersonMapper {

	void savePerson(SPerson sPerson);

	void saveFile(PersonAttachedFile attachedFile);

	List<SPerson> findPersons();
	
	SPerson findPersonByPersonId(Long person_id);

	AttachedFile findFileByPersonId(Long person_id);

	AttachedFile findFileByAttachedFileId(Long id);

	void removeAttachedFile(Long attachedFile_id);

	RentPerson findRentByPersonId(@Param("person_id")Long person_id, @Param("member_id")String member_id);
	
	void rentPerson(RentPerson rentPerson);

}
