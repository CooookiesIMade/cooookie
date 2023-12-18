package com.example.cookie.model.file;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlaceAttachedFile {
	private Long attachedfile_id;
	private String member_id;
	private Long place_id;
	private String original_filename;
	private String saved_filename;
	private Long file_size;
	
	
	public PlaceAttachedFile(AttachedFile attachedFile, Long place_id) {
		this.original_filename = attachedFile.getOriginal_filename();
		this.saved_filename = attachedFile.getSaved_filename();
		this.file_size = attachedFile.getFile_size();
		this.place_id = place_id;
	}
	
}