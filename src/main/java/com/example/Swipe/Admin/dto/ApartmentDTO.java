package com.example.Swipe.Admin.dto;

import com.example.Swipe.Admin.entity.Photo;
import com.example.Swipe.Admin.enums.*;
import com.example.Swipe.Admin.validation.FileExtension;
import jakarta.validation.constraints.*;
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
    @Min(100000)
    @Max(1000000)
    int price;
    int lcd;
    String nameLcd;
    List<PhotoDTO> photoList;
    int user;
    @FileExtension(value = {"jpg", "png"}, message = "Формат не корректный (.jpg,.png)")
    MultipartFile file;
    @FileExtension(value = {"jpg", "png"}, message = "Формат не корректный (.jpg,.png)")
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
    @NotBlank
    @NotEmpty
    @Pattern(message = "г.Город, р.Район, вул.Вулиця,1",regexp = "г\\.[A-Za-zА-Яа-я]+, р\\.[A-Za-zА-Яа-я]+, вул\\.[A-Za-zА-Яа-я]+,\\d+")
    String address;
    @Min(30)
    @Max(150)
    int totalArea;
    @Min(5)
    @Max(20)
    int kitchenArea;
    int frame;













}
