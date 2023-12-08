package com.example.cookie.controller;

import com.example.cookie.model.splace.Splace;
import com.example.cookie.service.SplaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SplaceController {

    private final SplaceService splaceService;

    @Autowired
    public SplaceController(SplaceService splaceService) {
        this.splaceService = splaceService;
    }

    // 장소등록 페이지 이동
    @GetMapping("/splace/register")
    public String showRegisterPage(Model model) {
        // 모델에 "splace" 객체를 추가하여 Thymeleaf 폼에서 사용할 수 있도록 함.
        model.addAttribute("splace", new Splace());
        return "splace/register";
    }

    @PostMapping("/splace/register")
    public String registerSplace(Splace splace, Model model) {
        splaceService.saveSplace(splace);
        model.addAttribute("place_name", splace.getPlace_name());
        model.addAttribute("place_cat", splace.getPlace_cat());
        model.addAttribute("place_count", splace.getPlace_count());
        model.addAttribute("place_address", splace.getPlace_address());
        model.addAttribute("place_content", splace.getPlace_content());
        model.addAttribute("place_price", splace.getPlace_price());
        return "splace/registered";
    }
}
