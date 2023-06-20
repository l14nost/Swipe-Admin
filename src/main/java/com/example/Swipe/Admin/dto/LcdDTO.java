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
    String name;
    @NotBlank
    @Size(min = 5, max = 1000)
    String description;
    StatusLCDType status;
    ClassType lcdClass;
    LCDType type;
    TechnologyType technology;
    TerritoryType territory;
    @NotBlank
    @NotEmpty
    @Pattern(message = "г.Город, р.Район, вул.Вулиця,1",regexp = "г\\.[A-Za-zА-Яа-я]+, р\\.[A-Za-zА-Яа-я]+, вул\\.[A-Za-zА-Яа-я]+,\\d+")
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
    String typePayment;
    @Pattern(regexp = "^[А-ЯЁA-Z][а-яёa-zA-Z]*$" )
    String appointment;
    @Pattern(regexp = "^[А-ЯЁA-Z][а-яёa-zA-Z]*$")
    String sumContract;
    @Pattern(regexp = "^[А-ЯЁA-Z][а-яёa-zA-Z]*$")
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
