package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.service.ContractorService;
import com.example.Swipe.Admin.service.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class ContractorController {
    private final ContractorService contractorService;

    public ContractorController(ContractorService contractorService) {
        this.contractorService = contractorService;
    }
}
