package com.example.Swipe.Admin.service;

import com.example.Swipe.Admin.repository.NewsRepo;
import com.example.Swipe.Admin.repository.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class NewsService {
    private final NewsRepo newsRepo;

    public NewsService(NewsRepo newsRepo) {
        this.newsRepo = newsRepo;
    }
}
