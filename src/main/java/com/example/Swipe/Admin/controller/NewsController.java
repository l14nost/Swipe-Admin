package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.service.impl.NewsServiceImpl;
import org.springframework.stereotype.Controller;

@Controller
public class NewsController {
    private final NewsServiceImpl newsServiceImpl;

    public NewsController(NewsServiceImpl newsServiceImpl) {
        this.newsServiceImpl = newsServiceImpl;
    }
}
