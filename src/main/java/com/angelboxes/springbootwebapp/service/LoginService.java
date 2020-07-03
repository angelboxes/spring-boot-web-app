package com.angelboxes.springbootwebapp.service;

import org.springframework.stereotype.Component;

@Component
public class LoginService {

    public boolean validate(String user, String password) {
        return user.equalsIgnoreCase("Angel") && password.equalsIgnoreCase("password");
    }
}
