package com.example.Swipe.Admin.service.impl;

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

    }
}
