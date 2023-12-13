package com.example.cookie.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.cookie.model.file.PlaceAttachedFile;
import com.example.cookie.model.splace.Splace;

@Mapper
public interface PlaceMapper {

	// 공유장소 저장
	void savePlace(Splace place);
	
	// 첨부파일 저장 
	void saveFile(PlaceAttachedFile attachedFile);
	
	// 장소 아이디로 첨부파일 검색
	PlaceAttachedFile findFileByPlaceId(Long place_id);
}
