package com.example.cookie.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.cookie.model.member.Member;
import com.example.cookie.model.review_pl.ReviewPlace;
import com.example.cookie.repository.ReviewMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("review")
public class ReviewController {
	
	private final ReviewMapper reviewMapper;
	
	@GetMapping("place")
	public String placeReview(@SessionAttribute("signInMember") Member signinMember,Model model) {
		
		model.addAttribute("signInMember",signinMember);
		
		List<ReviewPlace> reviews = reviewMapper.findReviewsById(signinMember.getMember_id());
		
		model.addAttribute("reviews", reviews);
		// log.info("reviews : {} ", reviews);
		
		return "user/review";
	}
	
	
	
	
	@PostMapping("place")
	public String placeReview(@SessionAttribute("signInMember") Member signInMember,
							  @ModelAttribute("data") ReviewPlace reviewPlace, BindingResult result) {
		
		// log.info("reviewPlace : {}" ,reviewPlace);
		ReviewPlace findReview = reviewMapper.findReviewPlaceById(reviewPlace.getPlace_id() , signInMember.getMember_id());
		
		if(findReview != null) {
			result.reject("alreadyReview", "이미 리뷰가 있습니다");
			return "redirect:/user/myrent";
		} else {
			reviewPlace.setMember_id(signInMember.getMember_id());			
			reviewMapper.savePlaceReview(reviewPlace);
			return "/user/review";
		}
	}
		
	@GetMapping("delete")
	public String deleteReview(@SessionAttribute("signInMember") Member signInMember , 
												@RequestParam Long review_id) {
		
		
		reviewMapper.removeReview(review_id);
		
		return "redirect:/user/review";
	
	}
	
}
