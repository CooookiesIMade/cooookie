package com.example.cookie.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.example.cookie.model.review.ReviewPerson;
import com.example.cookie.model.review.ReviewPlace;
import com.example.cookie.repository.ReviewMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("user")
public class ReviewController {
   
   private final ReviewMapper reviewMapper;
   
   @GetMapping("review")
   public String placeReview(@SessionAttribute("signInMember") Member signinMember,Model model) {
      
      model.addAttribute("signInMember",signinMember);
      
      List<ReviewPlace> reviews = reviewMapper.findReviewsById(signinMember.getMember_id());
      List<ReviewPerson> reviewPerson = reviewMapper.findPersonReviewsById(signinMember.getMember_id());
      
      model.addAttribute("reviews", reviews);
      model.addAttribute("reviewPerson", reviewPerson);

       log.info("reviews : {} ", reviews);
       log.info("reviewPerson : {}", reviewPerson);
      
      return "user/review";
   }
   
   @PostMapping("review")
   public ResponseEntity<String> placeReview(@SessionAttribute("signInMember") Member signInMember,
                                             @ModelAttribute ReviewPlace reviewPlace, BindingResult result) {

       ReviewPlace findReview = reviewMapper.findReviewPlaceById(reviewPlace.getPlace_id(), signInMember.getMember_id());

       if (findReview != null ) {
           return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 작성한 리뷰가 있습니다");
       } else {
           reviewPlace.setMember_id(signInMember.getMember_id());
           reviewMapper.savePlaceReview(reviewPlace);
           return ResponseEntity.ok("리뷰 등록되었습니다");
       }
   }
   
   @PostMapping("peReview")
   public ResponseEntity<String> placeReview(@SessionAttribute("signInMember") Member signInMember,
       																			 @ModelAttribute ReviewPerson reviewPerson, BindingResult result) {
  	  ReviewPerson findPersonReview = reviewMapper.findReviewPersonById(reviewPerson.getPerson_id(), signInMember.getMember_id());
  	  log.info("findPersonReview : {}", findPersonReview);
  	  if(findPersonReview != null) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 작성한 리뷰가 있습니다");
  	  } else {
  	  	 reviewPerson.setMember_id(signInMember.getMember_id());
  	  	 reviewMapper.savePersonReview(reviewPerson);
  	  	 return ResponseEntity.ok("리뷰 등록되었습니다");
  	  }
   }
   
   
   
   
   
      
   @GetMapping("delete")
   public ResponseEntity<String> deleteReview(@SessionAttribute("signInMember") Member signInMember,
                                             @RequestParam Long review_id) {

       reviewMapper.removeReview(review_id);

       return ResponseEntity.ok("리뷰 삭제가 완료되었습니다");
   }

   
}