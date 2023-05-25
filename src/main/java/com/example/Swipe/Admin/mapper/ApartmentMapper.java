package com.example.Swipe.Admin.mapper;

import com.example.Swipe.Admin.dto.AgentDTO;
import com.example.Swipe.Admin.dto.ApartmentDTO;
import com.example.Swipe.Admin.entity.Agent;
import com.example.Swipe.Admin.entity.Apartment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@RequiredArgsConstructor
public class ApartmentMapper  {
    public Agent toEntity(AgentDTO agentDTO){

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

    public static ApartmentDTO apply(Apartment apartment) {
        return ApartmentDTO.builder()
                .idApartment(apartment.getIdApartment())
                .number(apartment.getNumber())
                .description(apartment.getDescription())
                .price(apartment.getPrice())
                .user(ClientMapper.apply(apartment.getUser()))
                .photoList(apartment.getPhotoList().stream().map(PhotoMapper::apply).toList())

                .build();

    }
}
