package com.example.cookie.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.cookie.model.review_pl.ReviewPlace;

@Mapper
public interface ReviewMapper {

	// 공유장소 리뷰 
	void savePlaceReview(ReviewPlace reviewPlace);
	
	// 인재 리뷰 
	void savePersonReview();
}
