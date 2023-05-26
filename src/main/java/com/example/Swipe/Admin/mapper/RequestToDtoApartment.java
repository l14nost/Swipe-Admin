package com.example.Swipe.Admin.mapper;

import com.example.Swipe.Admin.dto.ApartmentDTO;

public class RequestToDtoApartment {
    public static ApartmentDTO toDto(ApartmentDTO request, ApartmentDTO apartmentDTO){
        request.setPhotoList(apartmentDTO.getPhotoList());
        request.setMainPhoto(apartmentDTO.getMainPhoto());
        return request;
    }
}
