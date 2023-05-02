package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.service.impl.LCDServiceImpl;
import org.springframework.stereotype.Controller;

@Controller
public class LCDController {
    private final LCDServiceImpl lcdServiceImpl;

    public LCDController(LCDServiceImpl lcdServiceImpl) {
        this.lcdServiceImpl = lcdServiceImpl;
    }
}
