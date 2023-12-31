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
import com.example.cookie.model.rent.RentPerson;
import com.example.cookie.model.rent.RentPlace;
import com.example.cookie.model.rent.RentPlaceRegister;
import com.example.cookie.model.review.ReviewPerson;
import com.example.cookie.model.sperson.PersonRegister;
import com.example.cookie.model.sperson.SPerson;
import com.example.cookie.model.splace.Splace;
import com.example.cookie.repository.ReviewMapper;
import com.example.cookie.service.PersonService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("person")
public class PersonController {

  private final PersonService personService;
  private final ReviewMapper reviewMapper;
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
	
	@GetMapping("category")
	public String category(Model model, @RequestParam("person_category") String person_category) {
		log.info("category : {}" ,person_category);
		List<SPerson> persons = personService.findPersonByCategory(person_category);
		log.info("persons : {}" , persons);
		model.addAttribute("persons", persons);
		
		return "person/list";
	}
	
	@GetMapping("detail")
	public String Detail(@RequestParam("person_id") Long person_id, Model model) {
		
		SPerson sPerson = personService.findPersonByPersonId(person_id);
		List<ReviewPerson> reviewPerson = reviewMapper.findReviewByPersonId(person_id);
    model.addAttribute("sPerson", sPerson);
    model.addAttribute("reviewPerson", reviewPerson);
		log.info("reviewPerson : {}", reviewPerson);
		return "person/detail";
	}
	
	@PostMapping("rent")
	public String rentPerson(@SessionAttribute("signInMember") Member signInMember, 
													@ModelAttribute("data") RentPerson rentPerson, BindingResult result)	{
		
		log.info("rentPerson : {} ", rentPerson);
		
		RentPerson findRentPerson = personService.findRentPersonById(rentPerson.getPerson_id(), signInMember.getMember_id());
		
		if(findRentPerson != null) {
			result.reject("alreadyRent", "이미 예약된 장소입니다");
			return "redirect:/person/detail";
		} else {
			rentPerson.setMember_id(signInMember.getMember_id());
			
			personService.rentPerson(rentPerson);
			return "redirect:/person/list";
		}
	}
}
