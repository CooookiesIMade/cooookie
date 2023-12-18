package com.example.cookie.model.file;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonAttachedFile {
	private Long attachedfile_id;
	private String member_id;
	private Long person_id;
	private String original_filename;
	private String saved_filename;
	private Long file_size;
	
	public PersonAttachedFile(AttachedFile attachedFile, Long person_id) {
		this.original_filename = attachedFile.getOriginal_filename();
		this.saved_filename = attachedFile.getSaved_filename();
		this.file_size = attachedFile.getFile_size();
		this.person_id = person_id;
	}
	
	
}