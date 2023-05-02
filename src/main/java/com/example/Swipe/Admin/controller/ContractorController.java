package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.service.impl.ContractorServiceImpl;
import org.springframework.stereotype.Controller;

@Controller
public class ContractorController {
    private final ContractorServiceImpl contractorServiceImpl;

    public ContractorController(ContractorServiceImpl contractorServiceImpl) {
        this.contractorServiceImpl = contractorServiceImpl;
    }
}
