package com.example.cookie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.cookie.model.member.Member;
import com.example.cookie.repository.ReviewMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("review")
public class ReviewController {
	
	private final ReviewMapper reviewMapper;
	
	@PostMapping("place")
	public String placeReview(@SessionAttribute("signInMember") Member signInMember) {
		
		return "place/review";
	}
	
}
