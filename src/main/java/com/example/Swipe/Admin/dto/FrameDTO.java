package com.example.Swipe.Admin.dto;

import com.example.Swipe.Admin.entity.Apartment;
import com.example.Swipe.Admin.entity.LCD;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class FrameDTO {
     int idFrame;

     int num;

     LcdDTO lcd;

     List<ApartmentDTO> apartmentList;
}
