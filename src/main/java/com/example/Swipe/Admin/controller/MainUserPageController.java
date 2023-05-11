package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.enums.TypeUser;
import com.example.Swipe.Admin.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
@RequiredArgsConstructor
public class MainUserPageController {
    private final UserServiceImpl userServiceImpl;


    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String usersMain(Model model) {
        model.addAttribute("users", userServiceImpl.findAllByType(TypeUser.CLIENT));
        model.addAttribute("contractors", userServiceImpl.findAllByType(TypeUser.CONTRACTOR));
        model.addAttribute("notaries", userServiceImpl.findAllByType(TypeUser.NOTARY));
        return "admin/user_main";
    }
}
