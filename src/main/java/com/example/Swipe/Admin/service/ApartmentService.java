package com.example.Swipe.Admin.service;

import com.example.Swipe.Admin.repository.ApartmentRepo;
import com.example.Swipe.Admin.repository.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class ApartmentService {
    private final ApartmentRepo apartmentRepo;

    public ApartmentService(ApartmentRepo apartmentRepo) {
        this.apartmentRepo = apartmentRepo;
    }
}
