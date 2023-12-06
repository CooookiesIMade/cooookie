package com.example.cookie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("person")
public class PersonController {

	@GetMapping("list")
	public String List() {
		
		return "person/list";
	}
	
}