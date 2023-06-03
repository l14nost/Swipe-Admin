package com.example.Swipe.Admin.mapper;

import com.example.Swipe.Admin.dto.AgentDTO;
import com.example.Swipe.Admin.dto.ApartmentDTO;
import com.example.Swipe.Admin.dto.LcdDTO;
import com.example.Swipe.Admin.entity.Agent;
import com.example.Swipe.Admin.entity.Apartment;
import com.example.Swipe.Admin.entity.LCD;
import com.example.Swipe.Admin.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@RequiredArgsConstructor
public class LcdMapper  {
//    private final ClientMapper clientMapper;
//    private final NewsMapper newsMapper;
//    private final PhotoMapper photoMapper;
//    private final DocumentMapper documentMapper;
//    private final FrameMapper frameMapper;
    public static LCD toEntity(LcdDTO lcd){
        LCD lcdRes = LCD.builder()
                .idLcd(lcd.getIdLcd())
                .name(lcd.getName())
                .description(lcd.getDescription())
                .status(lcd.getStatus())
                .lcdClass(lcd.getLcdClass())
                .technology(lcd.getTechnology())
                .territory(lcd.getTerritory())
                .distanceSea(lcd.getDistanceSea())
                .communal(lcd.getCommunal())
                .height(lcd.getHeight())
                .gas(lcd.getGas())
                .heating(lcd.getHeating())
                .sewerage(lcd.getSewerage())
                .waterSupply(lcd.getWaterSupply())
                .advantages(lcd.getAdvantages())
                .typePayment(lcd.getTypePayment())
                .appointment(lcd.getAppointment())
                .sumContract(lcd.getSumContract())
                .formalization(lcd.getFormalization())
                .type(lcd.getType())
                .address(lcd.getAddress())
                .build();
        return lcdRes;

    }

    public static LcdDTO apply(LCD lcd) {
        LcdDTO lcdDTO = LcdDTO.builder()
                .idLcd(lcd.getIdLcd())
                .name(lcd.getName())
                .description(lcd.getDescription())
                .status(lcd.getStatus())
                .lcdClass(lcd.getLcdClass())
                .technology(lcd.getTechnology())
                .territory(lcd.getTerritory())
                .distanceSea(lcd.getDistanceSea())
                .communal(lcd.getCommunal())
                .height(lcd.getHeight())
                .gas(lcd.getGas())
                .heating(lcd.getHeating())
                .sewerage(lcd.getSewerage())
                .waterSupply(lcd.getWaterSupply())
                .advantages(lcd.getAdvantages())
                .typePayment(lcd.getTypePayment())
                .appointment(lcd.getAppointment())
                .sumContract(lcd.getSumContract())
                .formalization(lcd.getFormalization())
                .type(lcd.getType())
                .address(lcd.getAddress())
                .mainPhoto(lcd.getMainPhoto())
                .build();
        if (lcd.getUser()!=null){
            lcdDTO.setContractor(lcd.getUser().getIdUser());
        }
        if (lcd.getNewsList()!=null){
            lcdDTO.setNewsList(lcd.getNewsList().stream().map(NewsMapper::apply).toList());
        }
        if (lcd.getPhotoList()!=null){
            lcdDTO.setPhotoList(lcd.getPhotoList().stream().map(PhotoMapper::apply).toList());
        }
        if (lcd.getDocuments()!=null){
            lcdDTO.setDocuments(lcd.getDocuments().stream().map(DocumentMapper::apply).toList());
        }
        if (lcd.getFrames()!=null){
            lcdDTO.setFrames(lcd.getFrames().stream().map(FrameMapper::apply).toList());
        }

        return lcdDTO;


    }
}
