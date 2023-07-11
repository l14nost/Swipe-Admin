package com.example.Swipe.Admin.dto;

import com.example.Swipe.Admin.entity.Photo;
import com.example.Swipe.Admin.enums.*;
import com.example.Swipe.Admin.validation.FileExtension;
import javax.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
public class ApartmentDTO {
    int  idApartment;
    @Min(value = 5, message = "Номер не должен быть меньше 5")
            @Max(value = 500, message = "Номер не должен превышать 500")
    int number;
    String mainPhoto;
    @NotBlank(message = "Не должно быть пустым")
    @Size(min = 5, max = 255, message = "Размер описание должен быть меньше 255 и больше 5")
    String description;
    @Min(value = 100000, message = "Цена не должна быть меньше 100000")
    @Max(value = 1000000, message = "Цена не должна превышать 1000000")
    int price;
    int lcd;
    String nameLcd;
    List<PhotoDTO> photoList;
    int user;
    @FileExtension(value = {"jpg", "png"}, message = "Формат некорректный (.jpg,.png)")
    MultipartFile file;
    @FileExtension(value = {"jpg", "png"}, message = "Формат некорректный (.jpg,.png)")
    List<MultipartFile> galleryPhoto;
    List<Integer> galleryListId;
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
    @NotBlank(message = "Не должно быть пустым")
    @Pattern(message = "Формат адреса некорректный. Пример(г.Город, р.Район, ул.Улица,1)",regexp = "г\\.[A-Za-zА-Яа-я]+, р\\.[A-Za-zА-Яа-я]+, ул\\.[A-Za-zА-Яа-я]+,\\d+")
    @Size(min = 5, max = 255, message = "Размер адресса должен быть меньше 255 и больше 5")
    String address;
    @Min(value = 30, message = "Общая площадь не должна быть меньше 30")
    @Max(value = 150, message = "Общая площадь не должна превышать 150")
    int totalArea;
    @Min(value = 5, message = "Площадь кухни не должна быть меньше 5")
    @Max(value = 20, message = "Площадь кухни не должна превышать 20")
    int kitchenArea;
    int frame;













}
