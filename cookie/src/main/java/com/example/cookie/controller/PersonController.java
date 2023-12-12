package com.example.cookie.controller;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.cookie.model.sperson.PersonRegister;
import com.example.cookie.model.sperson.SPerson;
import com.example.cookie.service.PersonService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("person")
public class PersonController {

	private final PersonService personService;
	
	@GetMapping("register")
	public String Register(Model model) {
		model.addAttribute("personRegister", new PersonRegister());
		return "person/register";
	}
	
	@PostMapping("register")
	public String Register(@Validated @ModelAttribute("personRegister") PersonRegister personRegister,
													BindingResult result) {
		
//		log.info("person : {}", personRegister);
//		log.info("file: {}", file);
		
		if(result.hasErrors()) {
			return "person/register";
		}
		
		SPerson sPerson = PersonRegister.toSPerson(personRegister);
		personService.savePerson(sPerson);
		return "redirect:/person/list";
	}
	
	@GetMapping("list")
	public String List() {
		
		return "person/list";
	}
	
}
