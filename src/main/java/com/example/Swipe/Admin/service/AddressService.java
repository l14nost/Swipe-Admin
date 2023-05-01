package com.example.Swipe.Admin.service;

import com.example.Swipe.Admin.repository.AddressRepo;
import com.example.Swipe.Admin.repository.AgentRepo;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    private final AddressRepo addressRepo;


    public AddressService(AddressRepo addressRepo) {
        this.addressRepo = addressRepo;
    }
}
