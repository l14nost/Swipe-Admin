package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.service.AddressService;
import com.example.Swipe.Admin.service.ApartmentService;
import org.springframework.stereotype.Controller;

@Controller
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }
}
