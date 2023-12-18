package com.example.cookie.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.example.cookie.model.member.Member;
import com.example.cookie.model.rent.RentPlace;
import com.example.cookie.model.rent.RentPlaceRegister;
import com.example.cookie.model.splace.Splace;
import com.example.cookie.model.splace.SplaceRegister;
import com.example.cookie.service.PlaceService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("place")
public class PlaceController {
	
	private final PlaceService placeService;
	@Value("${file.upload.path}")
	private String uploadPath;

	@GetMapping("register")
	public String Register(@SessionAttribute("signInMember") Member signinMember,  Model model) {
		model.addAttribute("splace", new Splace());
		model.addAttribute("member_id", signinMember.getMember_id());
		return "place/register";
	}
	
	@GetMapping("list")
	public String list(Model model) {
		List<Splace> findAllPlaces = placeService.findAllPlaces();
		model.addAttribute("place", new Splace());
		model.addAttribute("places", findAllPlaces);
		return "place/list";
	}
	
	@GetMapping("category")
	public String category(Model model, @RequestParam("category") String place_category) {
		log.info("category : {}" ,place_category);
		List<Splace> places = placeService.findPlaceByCategory(place_category);
		model.addAttribute("places", places);
		
		return "place/category";
	}
	
	
	@GetMapping("detail")
	public String detail(Model model, @RequestParam("place_id") Long place_id) {
		
		Splace place = placeService.findPlaceById(place_id);
		// log.info("상세 place 정보 : {}" , place);
		
		model.addAttribute("place", place);
		
		return "place/detail";
	}
	
	@PostMapping("register")
	public String Register(@SessionAttribute("signInMember") Member signInMember,
			@ModelAttribute("splace") SplaceRegister splaceRegister, BindingResult result, 
			@RequestParam(required = false) MultipartFile file) {
		
		log.info("splace : {}" , splaceRegister);
		
		if(result.hasErrors()) {
			return "place/register";
		}
		
		splaceRegister.setMember_id(signInMember.getMember_id());
		splaceRegister.setSaved_filename(file.getOriginalFilename());
		log.info("file : {}" , file);
		placeService.savePlace(SplaceRegister.toPlace(splaceRegister), file);
		
		return "redirect:/place/list";
	}
	
	
	@PostMapping("rent")
	public String rentPlace(@SessionAttribute("signInMember") Member signInMember, 
							@ModelAttribute("data") RentPlaceRegister rentPlaceRegister, BindingResult result)	{
		
		log.info("rentPlace : {} ", rentPlaceRegister);
		
		RentPlace findRentPlace = placeService.findRentPlaceById(rentPlaceRegister.getPlace_id(), signInMember.getMember_id());
		
		if(findRentPlace != null) {
			result.reject("alreadyRent", "이미 예약된 장소입니다");
			return "redirect:/place/detail";
		} else {
			rentPlaceRegister.setMember_id(signInMember.getMember_id());
			
			placeService.rentPlace(rentPlaceRegister);
			return "redirect:/place/list";
		}
		
	}
	
}