package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.service.NotaryService;
import org.springframework.stereotype.Controller;

@Controller
public class NotaryController {
    private final NotaryService notaryService;

    public NotaryController(NotaryService notaryService) {
        this.notaryService = notaryService;
    }
}
