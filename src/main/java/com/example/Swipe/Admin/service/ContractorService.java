package com.example.Swipe.Admin.service;

import com.example.Swipe.Admin.repository.ContractorRepo;
import org.springframework.stereotype.Service;

@Service
public class ContractorService {
    private final ContractorRepo contractorRepo;

    public ContractorService(ContractorRepo contractorRepo) {
        this.contractorRepo = contractorRepo;
    }
}
