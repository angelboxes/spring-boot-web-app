package com.angelboxes.springbootwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginMessage(@RequestParam String name, ModelMap model) {
        System.out.println("The name is " + name);
        model.addAttribute("name", name);
        return "login";
    }

}
