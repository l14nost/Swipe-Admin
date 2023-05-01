package com.example.Swipe.Admin.service;

import com.example.Swipe.Admin.repository.AddressRepo;
import com.example.Swipe.Admin.repository.NotaryRepo;
import org.springframework.stereotype.Service;

@Service
public class NotaryService {
    private final NotaryRepo notaryRepo;


    public NotaryService( NotaryRepo notaryRepo) {
        this.notaryRepo = notaryRepo;
    }
}
