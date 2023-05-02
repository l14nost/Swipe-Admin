package com.example.Swipe.Admin.service.impl;

import com.example.Swipe.Admin.entity.Agent;
import com.example.Swipe.Admin.entity.Apartment;
import com.example.Swipe.Admin.repository.ApartmentRepo;
import com.example.Swipe.Admin.service.ApartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApartmentServiceImpl implements ApartmentService {
    private final ApartmentRepo apartmentRepo;

    public ApartmentServiceImpl(ApartmentRepo apartmentRepo) {
        this.apartmentRepo = apartmentRepo;
    }

    @Override
    public List<Apartment> findAll() {
        return apartmentRepo.findAll();
    }

    @Override
    public Apartment findById(int id) {
        Optional<Apartment> apartment = apartmentRepo.findById(id);
        if(apartment.isPresent()){
            return apartment.get();
        }
        else {
            return Apartment.builder().build();
        }
    }

    @Override
    public void saveEntity(Apartment apartment) {
        apartmentRepo.save(apartment);
    }

    @Override
    public void deleteById(int id) {
        apartmentRepo.deleteById(id);
    }

    @Override
    public void updateEntity(Apartment apartment, int id) {

    }
}
