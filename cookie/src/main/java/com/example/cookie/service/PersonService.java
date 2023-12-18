package com.example.cookie.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.cookie.model.file.AttachedFile;
import com.example.cookie.model.file.PersonAttachedFile;
import com.example.cookie.model.sperson.SPerson;
import com.example.cookie.repository.PersonMapper;
import com.example.cookie.util.FileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PersonService {

	private final PersonMapper personMapper;
	private final FileService fileService;
	@Value("${file.upload.path}")
  private String uploadPath;

	
	@Transactional
	public void savePerson(SPerson sPerson, MultipartFile file) {
		personMapper.savePerson(sPerson);
		
	   if(file != null && file.getSize() > 0) {
     	//첨부파일을 서버에 저장
     	AttachedFile attachedFile = fileService.saveFile(file);
     		PersonAttachedFile saveFile = new PersonAttachedFile(attachedFile , sPerson.getPerson_id());
     	//첨부파일 내용을 데이터베이스에 저장
     	personMapper.saveFile(saveFile);
	   }
	}


	public List<SPerson> findPersons() {
		return personMapper.findPersons();
	}

	public AttachedFile findFileByAttachedFileId(Long id) {
		AttachedFile attachedFile = personMapper.findFileByAttachedFileId(id);
		return attachedFile;
	}
	
	public AttachedFile findFileByPersonId(Long person_id) {
		return personMapper.findFileByPersonId(person_id);
	}

	public SPerson findPersonByPersonId(Long person_id) {
		return personMapper.findPersonByPersonId(person_id);
	}
	
}