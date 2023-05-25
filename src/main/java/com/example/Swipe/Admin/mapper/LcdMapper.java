package com.example.Swipe.Admin.mapper;

import com.example.Swipe.Admin.dto.AgentDTO;
import com.example.Swipe.Admin.dto.ApartmentDTO;
import com.example.Swipe.Admin.dto.LcdDTO;
import com.example.Swipe.Admin.entity.Agent;
import com.example.Swipe.Admin.entity.Apartment;
import com.example.Swipe.Admin.entity.LCD;
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
    public static Agent toEntity(AgentDTO agentDTO){

        Agent agent = Agent.builder()
                .name(agentDTO.getName())
                .number(agentDTO.getNumber())
                .mail(agentDTO.getMail())
                .surname(agentDTO.getSurname())
                .type(agentDTO.getType())
                .build();
        if (agentDTO.getIdAgent()!=0){
            agent.setIdAgent(agentDTO.getIdAgent());
        }
        return agent;

    }

    public static LcdDTO apply(LCD lcd) {
        return LcdDTO.builder()
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
                .clientDTO(ClientMapper.apply(lcd.getUser()))
                .newsList(lcd.getNewsList().stream().map(NewsMapper::apply).toList())
                .photoList(lcd.getPhotoList().stream().map(PhotoMapper::apply).toList())
                .documents(lcd.getDocuments().stream().map(DocumentMapper::apply).toList())
                .frames(lcd.getFrames().stream().map(FrameMapper::apply).toList())
                .build();

    }
}
