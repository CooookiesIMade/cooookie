package com.example.cookie.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.cookie.model.review_pl.ReviewPlace;

@Mapper
public interface ReviewMapper {

	// 공유장소 리뷰 
	void savePlaceReview(ReviewPlace reviewPlace);
	
	// 장소아이디와 멤버아이디로 리뷰 찾기
	ReviewPlace findReviewPlaceById(@Param("place_id")Long place_id, @Param("member_id")String member_id);
	
	// 아이디로 작성한 리뷰들 가져오기 
	List<ReviewPlace> findReviewsById(@Param("member_id")String member_id);
	
	// 리뷰 삭제
	void removeReview(Long review_id);
	
	
	// 인재 리뷰 
	void savePersonReview();
	
	
}