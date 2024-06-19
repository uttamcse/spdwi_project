package com.example.documenttranslate.controller;

import com.example.documenttranslate.Entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {


    @GetMapping("/login")
    public String login() {
        return "login.html";
    }


}
