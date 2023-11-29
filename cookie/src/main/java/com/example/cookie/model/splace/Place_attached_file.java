package com.example.cookie.model.splace;

import lombok.Data;

@Data
public class Place_attached_file {

	private Long attachedfile_id;
	private String member_id;
	private String oriinal_filename;
	private String saved_filename;
	private Long file_size;
}
