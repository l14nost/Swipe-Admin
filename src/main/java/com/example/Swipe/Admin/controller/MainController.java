package com.example.Swipe.Admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/admin")
    public String main(Model model){
        return "admin/admin_banner";
    }
}
