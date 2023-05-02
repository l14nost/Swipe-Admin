package com.example.Swipe.Admin.service.impl;

import com.example.Swipe.Admin.entity.Address;
import com.example.Swipe.Admin.repository.AddressRepo;
import com.example.Swipe.Admin.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepo addressRepo;
    public AddressServiceImpl(AddressRepo addressRepo) {
        this.addressRepo = addressRepo;
    }

    @Override
    public List<Address> findAll() {
        return addressRepo.findAll();
    }

    @Override
    public Address findById(int id) {
        Optional<Address> address = addressRepo.findById(id);
        if(address.isPresent()){
            return address.get();
        }
        else {
            return Address.builder().city("").area("").number(0).street("").build();
        }
    }

    @Override
    public void saveEntity(Address address) {
        addressRepo.save(address);
    }

    @Override
    public void deleteById(int id) {
        addressRepo.deleteById(id);
    }

    @Override
    public void updateEntity(Address address, int id) {
        Optional<Address> addressOptional = addressRepo.findById(id);
        if(addressOptional.isPresent()){
            Address addressUpdate = addressOptional.get();
            if(address.getArea()!=null){
                addressUpdate.setArea(address.getArea());
            }
            if(address.getCity()!=null){
                addressUpdate.setCity(address.getCity());
            }
            if(address.getNumber()!=0){
                addressUpdate.setNumber(address.getNumber());
            }
            if(address.getStreet()!=null){
                addressUpdate.setCity(address.getCity());
            }
            addressRepo.saveAndFlush(addressUpdate);
        }
        else {
            Address addressUpdate = Address.builder().city("").area("").number(0).street("").build();
            addressRepo.saveAndFlush(addressUpdate);
        }
    }
}
