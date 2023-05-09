package com.example.Swipe.Admin.service.impl;

import com.example.Swipe.Admin.entity.Apartment;
import com.example.Swipe.Admin.entity.Contractor;
import com.example.Swipe.Admin.entity.LCD;
import com.example.Swipe.Admin.repository.LCDRepo;
import com.example.Swipe.Admin.service.LCDService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LCDServiceImpl implements LCDService {
    private final LCDRepo lcdRepo;

    public LCDServiceImpl(LCDRepo lcdRepo) {
        this.lcdRepo = lcdRepo;
    }

    @Override
    public List<LCD> findAll() {
        return lcdRepo.findAll();
    }

    @Override
    public LCD findById(int id) {
        Optional<LCD> lcd = lcdRepo.findById(id);
        if(lcd.isPresent()){
            return lcd.get();
        }
        else {
            return LCD.builder().build();
        }
    }

    @Override
    public void saveEntity(LCD lcd) {
        lcdRepo.save(lcd);
    }

    @Override
    public void deleteById(int id) {
        lcdRepo.deleteById(id);
    }

    @Override
    public void updateEntity(LCD lcd, int id) {
        Optional<LCD> lcdOptional = lcdRepo.findById(id);
        if(lcdOptional.isPresent()){
            LCD lcdUpdate = lcdOptional.get();
            if(lcd.getPhotosList()!=null){
                lcdUpdate.setPhotosList(lcd.getPhotosList());
            }
            if(lcd.getMainPhoto()!=null){
                lcdUpdate.setMainPhoto(lcd.getMainPhoto());
            }
            if(lcd.getAdvantages()!=null){
                lcdUpdate.setAdvantages(lcd.getAdvantages());
            }
            if(lcd.getAppointment() !=null){
                lcdUpdate.setAppointment(lcd.getAppointment());
            }
            if(lcd.getCommunal()!=null){
                lcdUpdate.setCommunal(lcd.getCommunal());
            }
            if(lcd.getGas()!=null){
                lcdUpdate.setGas(lcd.getGas());
            }
            if(lcd.getHeating()!=null){
                lcdUpdate.setHeating(lcd.getHeating());
            }
            if(lcd.getName()!=null){
                lcdUpdate.setName(lcd.getName());
            }
            if(lcd.getSewerage()!=null){
                lcdUpdate.setSewerage(lcd.getSewerage());
            }
            if(lcd.getStatus()!=null){
                lcdUpdate.setStatus(lcd.getStatus());
            }
            if(lcd.getTechnology()!=null){
                lcdUpdate.setTechnology(lcd.getTechnology());
            }
            if(lcd.getMainPhoto()!=null){
                lcdUpdate.setMainPhoto(lcd.getMainPhoto());
            }
            if(lcd.getDistanceSea()!=0){
                lcdUpdate.setDistanceSea(lcd.getDistanceSea());
            }
            if(lcd.getHeight()!=0){
                lcdUpdate.setHeight(lcd.getHeight());
            }
            if(lcd.getSumContract()!=0){
                lcdUpdate.setSumContract(lcd.getSumContract());
            }
            if(lcd.getTerritory()!=null){
                lcdUpdate.setTerritory(lcd.getTerritory());
            }
            if(lcd.getType()!=null){
                lcdUpdate.setType(lcd.getType());
            }
            if(lcd.getTypePayment()!=null){
                lcdUpdate.setTypePayment(lcd.getTypePayment());
            }
            if(lcd.getWaterSupply()!=null){
                lcdUpdate.setWaterSupply(lcd.getWaterSupply());
            }
            if(lcd.getDescription()!=null){
                lcdUpdate.setDescription(lcd.getDescription());
            }
            if(lcd.getLcdClass()!=null){
                lcdUpdate.setLcdClass(lcd.getLcdClass());
            }
            lcdRepo.saveAndFlush(lcdUpdate);
        }

    }
}
