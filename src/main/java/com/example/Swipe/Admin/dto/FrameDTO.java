package com.example.Swipe.Admin.dto;

import com.example.Swipe.Admin.entity.Apartment;
import com.example.Swipe.Admin.entity.LCD;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
@Data
@Builder
public class FrameDTO {
     int idFrame;

     int num;

     int idLcd;

     LcdDTO lcd;

     List<ApartmentDTO> apartmentList;
}
