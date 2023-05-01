package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.service.LCDService;
import com.example.Swipe.Admin.service.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class LCDController {
    private final LCDService lcdService;

    public LCDController(LCDService lcdService) {
        this.lcdService = lcdService;
    }
}
