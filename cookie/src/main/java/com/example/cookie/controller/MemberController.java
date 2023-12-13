package com.example.cookie.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
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
	
		@GetMapping("mypage")
		public String myPage(Model model, HttpServletRequest request) {
		    HttpSession session = request.getSession();
		    Member member = (Member) session.getAttribute("signInMember");

		    if (member != null) {
		        model.addAttribute("member", member);
		        return "user/mypage";
		    } else {
		        log.error("User not found in session");
		        return "redirect:/user/signin";
		    }
		}
		
		@PostMapping("/modify")
	    public String modifyMember(@Validated @ModelAttribute("member") Member modifiedMember, BindingResult result, HttpServletRequest request) {
	        HttpSession session = request.getSession();
	        Member currentMember = (Member) session.getAttribute("signInMember");

	        if (currentMember != null) {
	            // 현재 로그인한 사용자의 ID
	            String currentUserId = currentMember.getMember_id();

	            // 수정된 사용자 정보의 ID (입력 폼에서의 ID)
	            String modifiedUserId = modifiedMember.getMember_id();

	            // 현재 로그인한 사용자와 수정 폼에서의 사용자 ID가 일치하는지 확인
	            if (!currentUserId.equals(modifiedUserId)) {
	                log.error("Attempted to modify another user's information");
	                return "redirect:/user/mypage"; // 예외 처리 또는 경고 페이지로 리다이렉션
	            }

	            if (result.hasErrors()) {
	                return "user/modify"; // 입력 유효성 검사에 실패하면 수정 페이지로 다시 이동
	            }

	            // 현재 로그인한 사용자의 정보를 수정된 정보로 업데이트
	            memberService.updateMember(currentUserId, modifiedMember);

	            // 수정 완료 후 마이페이지로 리다이렉션
	            return "redirect:/user/mypage";
	        } else {
	            log.error("User not found in session");
	            return "redirect:/user/signin";
	        }
	    }

	}

