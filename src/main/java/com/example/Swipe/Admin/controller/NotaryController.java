package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.service.impl.NotaryServiceImpl;
import org.springframework.stereotype.Controller;

@Controller
public class NotaryController {
    private final NotaryServiceImpl notaryServiceImpl;

    public NotaryController(NotaryServiceImpl notaryServiceImpl) {
        this.notaryServiceImpl = notaryServiceImpl;
    }
}
