package com.example.Swipe.Admin.dto;

import com.example.Swipe.Admin.enums.*;
import com.example.Swipe.Admin.validation.FileExtension;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Data
@Builder
public class RequestLcdDto {
    int idLcd;
    @FileExtension(value = {"jpg", "png"}, message = "Формат не корректный (.jpg,.png)")
    MultipartFile file;
    @NotBlank
    @Pattern(regexp = "^[А-ЯЁA-Z][а-яёa-zA-Z]*$", message = "Имя должно содержать только буквы и начинаться с заглавной буквы")
    String name;
    @NotBlank
    String description;
    StatusLCDType status;
    ClassType lcdClass;
    LCDType type;
    TechnologyType technology;
    TerritoryType territory;
    int distanceSea;
    CommunalType communal;
    int height;
    GasType gas;
    HeatingType heating;
    HeatingType sewerage;
    HeatingType waterSupply;
    String advantages;
    String typePayment;
    String appointment;
    String sumContract;
    String formalization;
    int contractor;
//    @FileExtension(value = {"jpg", "png"}, message = "Формат не корректный (.jpg,.png)")
    List<MultipartFile> gallery;
//    @FileExtension(value = {"jpg", "png"}, message = "Формат не корректный (.jpg,.png)")
    List<MultipartFile> documents;
}
