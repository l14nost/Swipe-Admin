package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.service.impl.ContractorServiceImpl;
import com.example.Swipe.Admin.service.impl.NotaryServiceImpl;
import com.example.Swipe.Admin.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
@RequiredArgsConstructor
public class MainUserPageController {
    private final UserServiceImpl userServiceImpl;
    private final ContractorServiceImpl contractorServiceImpl;
    private final NotaryServiceImpl notaryServiceImpl;

    @GetMapping("/users")
    public String usersMain(Model model) {
        model.addAttribute("users", userServiceImpl.users());
        model.addAttribute("contractors", contractorServiceImpl.findAll());
        model.addAttribute("notaries", notaryServiceImpl.findAll());
        return "admin/user_main";
    }
}
