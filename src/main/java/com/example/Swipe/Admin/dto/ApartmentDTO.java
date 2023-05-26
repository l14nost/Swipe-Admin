package com.example.Swipe.Admin.dto;

import com.example.Swipe.Admin.entity.Photo;
import com.example.Swipe.Admin.enums.*;
import com.example.Swipe.Admin.validation.FileExtension;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
public class ApartmentDTO {
    int  idApartment;
    @Min(5)
            @Max(500)
    int number;
    String mainPhoto;
    @NotBlank
    @Size(min = 5, max = 1000)
    String description;
    int price;
    int lcd;
    List<PhotoDTO> photoList;
    int user;
    @FileExtension(value = {"jpg", "png"}, message = "Формат не корректный (.jpg,.png)")
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
    int frame;













}
