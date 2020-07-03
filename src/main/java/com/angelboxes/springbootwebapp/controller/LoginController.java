package com.angelboxes.springbootwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginMessage(ModelMap model) {
        return "login";
    }

    @PostMapping("/login")
    public String loginMessage(ModelMap model,   @RequestParam String name) {
        System.out.println("The name is " + name);
        model.addAttribute("name", name);
        return "welcome";
    }

}
