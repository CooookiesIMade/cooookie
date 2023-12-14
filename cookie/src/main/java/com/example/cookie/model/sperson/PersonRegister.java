package com.example.cookie.model.sperson;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PersonRegister {
	
	private String member_id;
	@NotEmpty(message ="제목을 입력해주세요")
	private String person_name;
	@NotNull(message="카테고리를 선택해주세요")
	private String person_category;
	@NotEmpty(message="상세정보를 입력해주세요")
	private String person_content;
	private String saved_filename;
	
	public static SPerson toSPerson(PersonRegister personRegister) {
		SPerson sPerson = new SPerson();
		sPerson.setMember_id(personRegister.getMember_id());
		sPerson.setPerson_name(personRegister.getPerson_name());
		sPerson.setPerson_category(personRegister.getPerson_category());
		sPerson.setPerson_content(personRegister.getPerson_content());
		sPerson.setSaved_filename(personRegister.getSaved_filename());
		return sPerson;
	}
}
