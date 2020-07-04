package com.angelboxes.springbootwebapp.controller;

import com.angelboxes.springbootwebapp.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping("/login")
    public String loginMessage(ModelMap model) {
        return "login";
    }

    @PostMapping("/login")
    public String loginMessage(ModelMap model, @RequestParam String name, @RequestParam String password) {
        model.addAttribute("name", name);
        model.addAttribute("password", password);
        boolean isValidUser = loginService.validate(name, password);
        if (!isValidUser) {
            model.addAttribute("errorMessage", "Invalid credentials");
            return "login";
        }
        return "welcome";
    }

}
