package com.example.Swipe.Admin.dto;

import com.example.Swipe.Admin.entity.Photo;
import com.example.Swipe.Admin.enums.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
public class ApartmentDTO {
    int  idApartment;
    int number;
    String description;
    int price;
    LcdDTO lcd;
    List<PhotoDTO> photoList;
    ClientDTO user;
    MultipartFile file;
    List<MultipartFile> galleryPhoto;
    FoundingDocument foundingDocument;
    State state;
    TypeApartment type;
    BalconyType balconyType;
    Calculation calculation;
    CountRoom countRoom;
    LayoutType layout;
    HeatingType heatingType;
    CommunicationType communicationType;
    Commission commission;
    int totalArea;
    int kitchenArea;














}
