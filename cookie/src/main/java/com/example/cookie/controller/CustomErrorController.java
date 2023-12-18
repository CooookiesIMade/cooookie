package com.example.cookie.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handle404Error() {
        return "error/error404"; 
    }

    public String handle500Error() {
        return "error/error500"; 
    }

    public String handle408Error() {
        return "error/error408"; 
    }
    
    public String handle503Error() {
        return "error/error503"; 
    }
    
    public String handle400Error() {
        return "error/error400"; 
    }
    
    public String handle403Error() {
        return "error/error403"; 
    }
    
    public String handle401Error() {
        return "error/error401"; 
    }
    
    public String handle501Error() {
        return "error/error501"; 
    }
    
    public String handle502Error() {
        return "error/error502"; 
    }
    
    public String getErrorPath() {
        return "/error";
    }
}
