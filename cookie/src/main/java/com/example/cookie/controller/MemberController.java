package com.example.cookie.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.example.cookie.model.member.Member;
import com.example.cookie.model.member.MemberSignIn;
import com.example.cookie.model.member.MemberSignUp;
import com.example.cookie.model.member.MemberValidator;
import com.example.cookie.model.rent.RentPlace;
import com.example.cookie.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("user")
public class MemberController {
	
	private final MemberService memberService;
	
	private final MemberValidator memberValidator;
	
	@Value("${file.upload.path}")
	private String uploadPath;
	// 회원가입 페이지 이동
	@GetMapping("signup")
	public String signUp(Model model) {
		model.addAttribute("member", new Member());
		return "user/signup";
	}
	
	// 회원가입 하기
	@PostMapping("signup")
	public String signUp(@Validated @ModelAttribute("member") MemberSignUp memberSignUp, BindingResult result, Model model,
						@RequestParam(required = false) MultipartFile file) {
		
		log.info("member : {}", memberSignUp);
		log.info("file : {}", file);
		
		
		if(result.hasErrors()) {
			return "user/signup";
		}
		memberSignUp.setSaved_filename(file.getOriginalFilename());
		memberService.saveMember(MemberSignUp.toMember(memberSignUp), file);
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
	public String signIn(@Validated @ModelAttribute("signin") MemberSignIn memberSignIn,
												BindingResult result, HttpServletRequest request) {
		
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
	
	@GetMapping("myrent")
	public String myRent(@SessionAttribute("signInMember") Member signinMember, Model model) {
		
		model.addAttribute("signInMember",signinMember);
		
		List<RentPlace> rentPlace = memberService.findRentPlaces(signinMember.getMember_id());
		// log.info("rentPlace : {}", rentPlace);
		model.addAttribute("rentPlace", rentPlace);
		
		
		return "user/myrent";
	}
	
	
	@GetMapping("mypage")
	public String myPage(Model model, HttpSession session) {
	    Member member = (Member) session.getAttribute("signInMember");

	    if (member != null) {
	        model.addAttribute("member", member);
	        return "user/mypage";
	    } else {
	        log.error("User not found in session");
	        return "redirect:/user/signin";
	    }
	}

	
	@RequestMapping(value = "update", method = {RequestMethod.GET, RequestMethod.POST})
	public String update(@Validated @ModelAttribute("updatedMember") Member updatedMember, BindingResult result, Model model, HttpServletRequest request) {
	    HttpSession session = request.getSession();
	    Member currentMember = (Member) session.getAttribute("signInMember");

	    if (currentMember != null) {
	        // 기존 정보를 update 페이지로 전달
	        model.addAttribute("member", currentMember);
	        // 수정 폼을 위한 빈 객체도 추가
	        model.addAttribute("updatedMember", updatedMember);

	        if (request.getMethod().equals("POST")) {
	            // POST 요청 시에만 유효성 검사 수행
	            // 기존 로그인한 사용자 정보 가져오기
	            String currentUserId = currentMember.getMember_id();

	            // 유효성 검사 수행
	            memberValidator.validate(updatedMember, result);

	            if (!result.hasErrors()) {
	                // 세션에 업데이트된 정보 저장
	                session.setAttribute("signInMember", updatedMember);
	                // DB에 업데이트 수행
	                memberService.updateMember(currentUserId, updatedMember);
	                // 수정 완료 후 메인으로 리다이렉션
	                return "redirect:/main";
	            }
	        }

	        return "user/update";
	    } else {
	        log.error("User not found in session");
	        return "redirect:/user/signin";
	    }
	}

	
	
}
