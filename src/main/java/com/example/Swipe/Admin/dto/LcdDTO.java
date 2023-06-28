package com.example.Swipe.Admin.dto;

import com.example.Swipe.Admin.entity.News;
import com.example.Swipe.Admin.enums.*;
import com.example.Swipe.Admin.validation.FileExtension;
import javax.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
public class LcdDTO {
    int idLcd;

    String mainPhoto;
    @FileExtension(value = {"jpg", "png"}, message = "Формат не корректный (.jpg,.png)")
    MultipartFile file;
    @NotBlank
    @Pattern(regexp = "^[А-ЯЁA-Z][а-яёa-zA-Z]*$", message = "Имя должно содержать только буквы и начинаться с заглавной буквы")
    @Size(min = 2, max = 50)
    String name;
    @NotBlank
    @Size(min = 5, max = 255)
    String description;
    StatusLCDType status;
    ClassType lcdClass;
    LCDType type;
    TechnologyType technology;
    TerritoryType territory;
    @NotBlank
    @NotEmpty
    @Pattern(message = "г.Город, р.Район, ул.Улица,1",regexp = "г\\.[A-Za-zА-Яа-я]+, р\\.[A-Za-zА-Яа-я]+, ул\\.[A-Za-zА-Яа-я]+,\\d+")
    @Size(min = 2, max = 50)
    String address;
    @Min(5)
    @Max(150)
    int distanceSea;
    CommunalType communal;
    @Min(3)
    @Max(5)
    int height;
    GasType gas;
    HeatingType heating;
    HeatingType sewerage;
    HeatingType waterSupply;
    @NotBlank
    String advantages;
    @Pattern(regexp = "^[А-ЯЁA-Z][а-яёa-zA-Z]*$" )
    @Size(min = 1,max = 255)
    String typePayment;
    @Pattern(regexp = "^[А-ЯЁA-Z][а-яёa-zA-Z]*$" )
    @Size(min = 1,max = 255)
    String appointment;
    @Pattern(regexp = "^[А-ЯЁA-Z][а-яёa-zA-Z]*$")
    @Size(min = 1,max = 255)
    String sumContract;
    @Pattern(regexp = "^[А-ЯЁA-Z][а-яёa-zA-Z]*$")
    @Size(min = 1,max = 255)
    String formalization;
    int contractor;
    List<NewsDTO> newsList;
    List<PhotoDTO> photoList;
    @FileExtension(value = {"jpg", "png"}, message = "Формат не корректный (.jpg,.png)")
    List<MultipartFile> gallery;
    List<DocumentDTO> documents;
    @FileExtension(value = {"pdf", "doc", "docx", "xlsx"}, message = "Формат не корректный (.pdf,.doc,.docx,.xlsx)")
    List<MultipartFile> documentsFiles;
    List<FrameDTO> frames;
}
