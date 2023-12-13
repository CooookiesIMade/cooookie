package com.example.cookie.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.example.cookie.model.member.Member;
import com.example.cookie.model.sperson.PersonRegister;
import com.example.cookie.model.sperson.SPerson;
import com.example.cookie.service.PersonService;
import com.example.cookie.util.FileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("person")
public class PersonController {

	private final PersonService personService;
	private final FileService fileService;
  @Value("${file.upload.path}")
  private String uploadPath;
	
	@GetMapping("register")
	public String Register(Model model) {
		model.addAttribute("personRegister", new PersonRegister());
		return "person/register";
	}
	
	@PostMapping("register")
	public String Register(@SessionAttribute(value = "signInMember", required = false) Member signInMember,
													@Validated @ModelAttribute("personRegister") PersonRegister personRegister,
													BindingResult result,
													@RequestParam(required = false) MultipartFile file) {
		
//		log.info("person : {}", personRegister);
		log.info("file: {}", file);
		
		if(result.hasErrors()) {
			return "person/register";
		}
		
		SPerson sPerson = PersonRegister.toSPerson(personRegister);
		sPerson.setMember_id(signInMember.getMember_id());
		log.info("person2 : {}", personRegister);
		personService.savePerson(sPerson, file);
		return "redirect:/person/list";
	}
	
	@GetMapping("list")
	public String List() {
		
		return "person/list";
	}
	
}
