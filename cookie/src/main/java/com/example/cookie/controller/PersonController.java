package com.example.cookie.controller;


import java.util.List;

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

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("person")
public class PersonController {

  private final PersonService personService;
	
  @Value("${file.upload.path}")
  private String uploadPath;
	
	@GetMapping("register")
	public String Register(@SessionAttribute("signInMember") Member signinMember, Model model) {
		model.addAttribute("personRegister", new PersonRegister());
		model.addAttribute("member_id", signinMember.getMember_id());
		return "person/register";
	}
	
	@PostMapping("register")
	public String Register(@SessionAttribute(value = "signInMember", required = false) Member signInMember,
													@Validated @ModelAttribute("personRegister") PersonRegister personRegister,
													BindingResult result,
													@RequestParam(required = false) MultipartFile file) {
		
		log.info("person : {}", personRegister);
		log.info("file: {}", file);
		
		if(result.hasErrors()) {
			return "person/register";
		}
		
		SPerson sPerson = PersonRegister.toSPerson(personRegister);
		sPerson.setMember_id(signInMember.getMember_id());
		sPerson.setSaved_filename(file.getOriginalFilename());
		log.info("person2 : {}", personRegister);
		personService.savePerson(sPerson, file);
		return "redirect:/person/list";
	}
	
	@GetMapping("list")
	public String List(@SessionAttribute(value = "signInMember", required = false) Member signInMember,
      							Model model) {
    // 데이터베이스에 저장된 모든 Board 객체를 리스트 형태로 받는다.
    List<SPerson> persons = personService.findPersons();
    // Board 리스트를 model 에 저장한다.
    model.addAttribute("persons", persons);
		return "person/list";
	}
	
	@GetMapping("detail")
	public String Detail(Model model) {
		
		return "person/detail";
	}
}
