package com.example.Swipe.Admin.service;

import com.example.Swipe.Admin.repository.LCDRepo;
import com.example.Swipe.Admin.repository.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class LCDService {
    private final LCDRepo lcdRepo;

    public LCDService(LCDRepo lcdRepo) {
        this.lcdRepo = lcdRepo;
    }
}
