package com.example.Swipe.Admin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/api/v1/login")
public class SignController {
//    @GetMapping("/login")
//    public String main(Model model){
//        return "admin/login";
//    }
    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("admin/login");
    }
}
