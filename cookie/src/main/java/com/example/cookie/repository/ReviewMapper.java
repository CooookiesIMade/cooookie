package com.example.cookie.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.cookie.model.review.ReviewPerson;
import com.example.cookie.model.review.ReviewPlace;

@Mapper
public interface ReviewMapper {

	// 공유장소 리뷰 
	void savePlaceReview(ReviewPlace reviewPlace);
	
	// 인재 리뷰 
	void savePersonReview(ReviewPerson reviewPerson);
	
	// 장소아이디와 멤버아이디로 리뷰 찾기
	ReviewPlace findReviewPlaceById(@Param("place_id")Long place_id, @Param("member_id")String member_id);
	
	// 사람아이디와 멤버아이디로 리뷰 찾기
	ReviewPerson findReviewPersonById(@Param("person_id")Long person_id, @Param("member_id")String member_id);
	
	
	// 사람아이디로 해당하는 리뷰가져오기
	List<ReviewPerson> findReviewByPersonId(Long person_id);

	
	// 아이디로 작성한 리뷰들 가져오기(장소) 
	List<ReviewPlace> findReviewsById(@Param("member_id")String member_id);
	
	// 아이디로 작성한 리뷰들 가져오기(사람) 
	List<ReviewPerson> findPersonReviewsById(@Param("member_id")String member_id);
	
	// 전체 리뷰 가져오기(장소)
	List<ReviewPlace> findAllReview();
	
	// 전체 리뷰 가져오기(사람)
	List<ReviewPerson> findAllPersonReview();
	
	// 리뷰 삭제
	void removeReview(Long review_id);

	List<ReviewPlace> findReviewByPlaceId(Long place_id);
	
}
