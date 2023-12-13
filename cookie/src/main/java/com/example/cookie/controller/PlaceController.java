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
import com.example.cookie.model.splace.Splace;
import com.example.cookie.model.splace.SplaceRegister;
import com.example.cookie.service.PlaceService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
	public String List() {
		
		return "place/list";
	}
	
	@GetMapping("detail")
	public String Detail(Model model) {
		
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
		
		log.info("file : {}" , file);
		placeService.savePlace(SplaceRegister.toPlace(splaceRegister), file);
		
		return "redirect:/place/list";
	}
}
