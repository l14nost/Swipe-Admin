package com.example.Swipe.Admin.controller.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @GetMapping("/login")
    public  String logPage(Model model){
        return "admin/login";
    }
//    @PostMapping("/register")
//    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
//        return ResponseEntity.ok(authenticationService.register(request));
//    }
//    @PostMapping("/authenticate")
//    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request){
//        return ResponseEntity.ok(authenticationService.authenticate(request));
//    }
    @PostMapping("/login")
    public String login(@RequestParam String login, @RequestParam String password){
        AuthenticationRequest request = new AuthenticationRequest();
        request.setLogin(login);
        request.setPassword(password);
        ResponseEntity.ok(authenticationService.authenticate(request));
        return "redirect:/users";
    }
}
