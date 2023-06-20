package com.example.Swipe.Admin.mapper;

import com.example.Swipe.Admin.dto.AgentDTO;
import com.example.Swipe.Admin.dto.ApartmentDTO;
import com.example.Swipe.Admin.entity.Agent;
import com.example.Swipe.Admin.entity.Apartment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ApartmentMapper  {
    public static Apartment toEntity(ApartmentDTO apartment){
        Apartment apartment1 = Apartment.builder()
                .idApartment(apartment.getIdApartment())
                .number(apartment.getNumber())
                .description(apartment.getDescription())
                .price(apartment.getPrice())
                .mainPhoto(apartment.getMainPhoto())
                .balconyType(apartment.getBalconyType())
                .calculation(apartment.getCalculation())
                .commission(apartment.getCommission())
                .communicationType(apartment.getCommunicationType())
                .countRoom(apartment.getCountRoom())
                .heatingType(apartment.getHeatingType())
                .kitchenArea(apartment.getKitchenArea())
                .state(apartment.getState())
                .layout(apartment.getLayout())
                .totalArea(apartment.getTotalArea())
                .foundingDocument(apartment.getFoundingDocument())
                .type(apartment.getType())
                .address(apartment.getAddress())
                .build();
        return apartment1;


    }

    public static ApartmentDTO apply(Apartment apartment) {
        ApartmentDTO apartmentDTO = ApartmentDTO.builder()
                .idApartment(apartment.getIdApartment())
                .number(apartment.getNumber())
                .description(apartment.getDescription())
                .price(apartment.getPrice())
                .mainPhoto(apartment.getMainPhoto())
                .balconyType(apartment.getBalconyType())
                .calculation(apartment.getCalculation())
                .commission(apartment.getCommission())
                .communicationType(apartment.getCommunicationType())
                .countRoom(apartment.getCountRoom())
                .heatingType(apartment.getHeatingType())
                .kitchenArea(apartment.getKitchenArea())
                .state(apartment.getState())
                .layout(apartment.getLayout())
                .totalArea(apartment.getTotalArea())
                .foundingDocument(apartment.getFoundingDocument())
                .type(apartment.getType())
                .address(apartment.getAddress())
                .build();
        if (apartment.getFrame()!=null){
            apartmentDTO.setFrame(apartment.getFrame().getIdFrame());
        }else apartmentDTO.setFrame(0);
        if (apartment.getLcd()!=null){
            apartmentDTO.setLcd(apartment.getLcd().getIdLcd());
            apartmentDTO.setNameLcd(apartment.getLcd().getName());
        }else apartmentDTO.setLcd(0);
        if (apartment.getUser()!=null){
            apartmentDTO.setUser(apartment.getUser().getIdUser());
        }
        if (apartment.getPhotoList()!=null){
            apartmentDTO.setPhotoList(apartment.getPhotoList().stream().map(PhotoMapper::apply).collect(Collectors.toList()));
        }
        return apartmentDTO;

    }
}
