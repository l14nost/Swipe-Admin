package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.service.ApartmentService;
import com.example.Swipe.Admin.service.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class ApartmentController {
    private final ApartmentService apartmentService;

    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }
}
