package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.service.impl.AddressServiceImpl;
import org.springframework.stereotype.Controller;

@Controller
public class AddressController {
    private final AddressServiceImpl addressServiceImpl;

    public AddressController(AddressServiceImpl addressServiceImpl) {
        this.addressServiceImpl = addressServiceImpl;
    }
}
