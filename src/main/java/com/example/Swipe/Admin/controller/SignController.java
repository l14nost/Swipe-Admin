package com.example.Swipe.Admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignController {
    @GetMapping("/")
    public String main(Model model){
        return "admin/sign_in";
    }
}
