package com.example.cookie.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.cookie.model.file.PlaceAttachedFile;
import com.example.cookie.model.rent.RentPlace;
import com.example.cookie.model.rent.RentPlaceRegister;
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
	List<Splace> findPlaceByCategory(String place_category);
	
	// 장소 예약하기 
	void rentPlace(RentPlaceRegister rentPlaceRegister);
	
	// 장소 아이디와 멤버아이디로 찾기 
	RentPlace findRentPlaceById(@Param("place_id")Long place_id ,@Param("member_id")String member_id);
}