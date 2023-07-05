package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.dto.AdminDto;
import com.example.Swipe.Admin.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final UserServiceImpl userService;


    @GetMapping("/profile")
    public String profileAdmin(Model model){
        model.addAttribute("user",userService.getCurrentUser());
        return "admin/admin_profile";
    }
    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute("user") @Valid AdminDto adminDto, BindingResult result, Model model){
        result = userService.uniqueMail(adminDto.getMail(), result, adminDto.getIdUser(),"update");
        if (!adminDto.getPassword().equals(adminDto.getConfirmPassword())){
           result.addError(new FieldError("user", "password", "Пароли не совпадают"));
        }
        else{
            if (new BCryptPasswordEncoder().matches(adminDto.getPassword(), userService.getCurrentUser().getPassword())){
                result.addError(new FieldError("user", "password", "Пароль уже используеться"));
            }
        }
        if (result.hasErrors()){
            model.addAttribute("user",adminDto);
            return "admin/admin_profile";
        }
        if (adminDto.getMail().equals( adminMail()) && (new BCryptPasswordEncoder().matches(adminDto.getPassword(), userService.getCurrentUser().getPassword()) || adminDto.getPassword().isEmpty())){
            return "redirect:/statistics";
        }
        userService.updateAdmin(adminDto);
        return "redirect:/logout";
    }
    public static String adminMail(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }




}
