package com.example.cookie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String Home() {
		return "index";
	}
	
	@GetMapping("/main")
	public String Main() {
		return "main";
	}

}
