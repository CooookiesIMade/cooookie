package com.example.cookie.model.sperson;

import lombok.Data;

@Data
public class Attached_file {

	private Long attachedfile_id;
	private String member_id;
	private Long person_id;
	private Long place_id;
	private String original_filename;
	private String saved_filename;
	private Long file_size;
}
