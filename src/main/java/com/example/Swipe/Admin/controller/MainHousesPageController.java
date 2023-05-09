package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.service.impl.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainHousesPageController {
    private final LCDServiceImpl lcdService;
    private final ApartmentServiceImpl apartmentService;

    @GetMapping("/houses")
    public String housesMain(Model model) {
        model.addAttribute("lcds",lcdService.findAll());
        model.addAttribute("apartments", apartmentService.findAllByFrame());
        return "admin/houses_main";
    }
}
