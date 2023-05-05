package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.controller.auth.AuthenticationRequest;
import com.example.Swipe.Admin.controller.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//@RequiredArgsConstructor
//@RequestMapping("/admin")
//public class LoginController {
//    private final AuthenticationService authenticationService;
//    @GetMapping("/login")
//    public String getLoginPage(){
//        return "admin/login";
//    }
//    @GetMapping("/success")
//    public String getSuccess(){
//        return "admin/admin_main";
//    }
//    @GetMapping("admin/login")
//    public String login(@RequestParam String login, @RequestParam String password){
//        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
//        authenticationRequest.setLogin(login);
//        authenticationRequest.setPassword(password);
//        authenticationService.authenticate(authenticationRequest);
//        return "admin/admin_main";
//    }
//}
