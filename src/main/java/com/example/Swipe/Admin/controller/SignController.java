package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.controller.auth.AuthenticationRequest;
import com.example.Swipe.Admin.token.TokenRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/admin")
@RequiredArgsConstructor
//@RequestMapping("/test")
public class SignController {
//    private final AuthenticationService authenticationService;
//    private final TokenRepo tokenRepo;
//    @GetMapping("/demo")
//    public ResponseEntity<String> sayHello(){
//        return ResponseEntity.ok("hi bro");
//    }
//
//    @GetMapping("/login")
//    public  String logPage(Model model){
//        return "admin/login";
//    }
//    @PostMapping("/login")
//    public String login(@RequestParam String login, @RequestParam String password, Model model){
//        AuthenticationRequest request = new AuthenticationRequest();
//        request.setLogin(login);
//        request.setPassword(password);
////        authenticationService.authenticate(request);
//
//        String token = tokenRepo.findAllValidTokensByAdmin(18).get(0).getToken();
//        System.out.println(token);
//        model.addAttribute("token", token);
//        return "admin/admin_main";
//    }
//    @PostMapping("admin/main")
//    public String login(@RequestParam String login, @RequestParam String password, Model model){
//        AuthenticationRequest request = new AuthenticationRequest();
//        request.setLogin(login);
//        request.setPassword(password);
//        authenticationService.authenticate(request);
//
//        String token = tokenRepo.findAllValidTokensByAdmin(18).get(0).getToken();
//        System.out.println(token);
//        model.addAttribute("token", token);
//        return "admin/admin_main";
//    }
    @GetMapping("/login")
    public String loginPage(Model model){
        return "admin/login";
    }
}
