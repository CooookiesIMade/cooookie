package com.example.cookie.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.cookie.model.member.Member;
import com.example.cookie.model.member.MemberSignIn;
import com.example.cookie.model.member.MemberSignUp;
import com.example.cookie.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("user")
public class MemberController {
	
	private final MemberService memberService;

	// 회원가입 페이지 이동
	@GetMapping("signup")
	public String signUp(Model model) {
		model.addAttribute("member", new Member());
		return "user/signup";
	}
	
	// 회원가입 하기
	@PostMapping("signup")
	public String signUp(@Validated @ModelAttribute("member") MemberSignUp memberSignUp, BindingResult result, Model model) {
		
		log.info("member : {}", memberSignUp);
		
		
		if(result.hasErrors()) {
			return "user/signup";
		}
		memberService.saveMember(MemberSignUp.toMember(memberSignUp));
		return "redirect:/";
		
	}

	// 로그인 페이지 이동
	@GetMapping("signin")
	public String signIn(Model model) {
		model.addAttribute("signin", new MemberSignIn());
		return "user/signin";
	}
	
	// 로그인 하기
	@PostMapping("signin")
	public String signIn(@Validated @ModelAttribute("signin") MemberSignIn memberSignIn, BindingResult result,
						 HttpServletRequest request) {
		
		// 로그인 검증 
		Member findMember = memberService.findMember(memberSignIn.getMember_id());
		
		if(findMember == null) {
			result.rejectValue("member_id", "idError", "해당하는 ID가 없음");
		} else if(!findMember.getMember_pw().equals(memberSignIn.getMember_pw())) {
			result.rejectValue("member_pw","pwError", "패스워드가 다름");
		} 
		
		if(result.hasErrors()) {
			return "user/signin";
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("signInMember", findMember);
		
		return "redirect:/main";
	}
	
	// 로그아웃 하기
	@GetMapping("logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
	
		session.invalidate();
		
		return "redirect:/";
	}

	
	
}
