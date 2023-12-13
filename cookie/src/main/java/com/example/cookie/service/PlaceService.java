package com.example.cookie.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.cookie.model.file.AttachedFile;
import com.example.cookie.model.file.PlaceAttachedFile;
import com.example.cookie.model.splace.Splace;
import com.example.cookie.repository.PlaceMapper;
import com.example.cookie.util.FileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class  PlaceService {
	
	private final PlaceMapper placeMapper;
	private final FileService fileService;
	
	@Value("${file.upload.path}")
	private String uploadPath;
	
	@Transactional
	public void savePlace(Splace place, MultipartFile file) {
		
		placeMapper.savePlace(place);
		
		// 첨부파일 저장
		if(file != null && file.getSize() > 0) {
			AttachedFile attachedFile = fileService.saveFile(file);
				PlaceAttachedFile saveFile = new PlaceAttachedFile(attachedFile, place.getPlace_id());
				
				// 첨부파일 내용을 데이터베이스에 저장
				placeMapper.saveFile(saveFile);
		}
		
		
		
	}
	
}
