package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.service.impl.ApartmentServiceImpl;
import org.springframework.stereotype.Controller;

@Controller
public class ApartmentController {
    private final ApartmentServiceImpl apartmentServiceImpl;

    public ApartmentController(ApartmentServiceImpl apartmentServiceImpl) {
        this.apartmentServiceImpl = apartmentServiceImpl;
    }
}
