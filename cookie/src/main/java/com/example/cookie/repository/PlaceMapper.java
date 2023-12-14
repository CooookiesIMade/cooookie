package com.example.cookie.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.cookie.model.file.PlaceAttachedFile;
import com.example.cookie.model.splace.Splace;

@Mapper
public interface PlaceMapper {

	// 공유장소 저장
	void savePlace(Splace place);
	
	// 첨부파일 저장 
	void saveFile(PlaceAttachedFile attachedFile);
	
	// 장소 아이디로 장소 검색
	Splace findPlaceById(Long place_id);
	
	// 장소 아이디로 첨부파일 검색
	PlaceAttachedFile findFileByPlaceId(Long place_id);
	
	// 전체 공유 장소 가져오기
	List<Splace> findAllPlace();
	
	// 전체 사진 파일 가져오기 
	List<PlaceAttachedFile> findFiles();
	
	// 장소 카테고리로 공유장소 가져오기
	Splace findPlaceByCategory(String place_category);
}
