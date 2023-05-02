package com.example.Swipe.Admin.service.impl;

import com.example.Swipe.Admin.entity.Agent;
import com.example.Swipe.Admin.entity.Contractor;
import com.example.Swipe.Admin.repository.ContractorRepo;
import com.example.Swipe.Admin.service.ContractorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractorServiceImpl implements ContractorService {
    private final ContractorRepo contractorRepo;

    public ContractorServiceImpl(ContractorRepo contractorRepo) {
        this.contractorRepo = contractorRepo;
    }

    public List<Contractor> contractors(){
        return contractorRepo.findAll();
    }

    @Override
    public List<Contractor> findAll() {
        return contractorRepo.findAll();
    }

    @Override
    public Contractor findById(int id) {
        Optional<Contractor> contractor = contractorRepo.findById(id);
        if(contractor.isPresent()){
            return contractor.get();
        }
        else {
            return Contractor.builder().build();
        }
    }

    @Override
    public void saveEntity(Contractor contractor) {
        contractorRepo.save(contractor);
    }

    @Override
    public void deleteById(int id) {
        contractorRepo.deleteById(id);
    }

    @Override
    public void updateEntity(Contractor contractor, int id) {

    }
}
