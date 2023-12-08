package com.example.cookie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("place")
public class PlaceController {

	@GetMapping("register")
	public String Register(Model model) {
		
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
}
