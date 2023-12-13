package com.example.cookie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.cookie.model.member.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	
	@GetMapping("/")
	public String Home() {
		return "index";
	}
	
	@GetMapping("/main")
	public String Main(@SessionAttribute("signInMember") Member signinMember, Model model) {
		model.addAttribute("signInMember", signinMember);
		return "main";
	}

}
