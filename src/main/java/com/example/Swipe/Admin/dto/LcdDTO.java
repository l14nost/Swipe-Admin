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
    @NotBlank(message = "Не должно быть пустым")
    @Pattern(regexp = "^[А-ЯЁA-Z][а-яёa-z]*$", message = "Должно содержать только буквы, начинаться и иметь только одну заглавную букву")
    @Size(min = 2, max = 50, message = "Размер названия должен быть меньше 50 и больше 5")
    String name;
    @NotBlank(message = "Не должно быть пустым")
    @Size(min = 5, max = 255, message = "Размер описание должен быть меньше 255 и больше 5")
    String description;
    StatusLCDType status;
    ClassType lcdClass;
    LCDType type;
    TechnologyType technology;
    TerritoryType territory;
    @NotBlank(message = "Не должно быть пустым")
    @Pattern(message = "Формат адреса некорректный. Пример(г.Город, р.Район, ул.Улица,1)",regexp = "г\\.[A-Za-zА-Яа-я]+, р\\.[A-Za-zА-Яа-я]+, ул\\.[A-Za-zА-Яа-я]+,\\d+")
    @Size(min = 2, max = 255, message = "Размер адрес должен быть меньше 255 и больше 2")
    String address;
    @Min(value = 5, message = "Дистанция до моря не должна быть меньше 5")
    @Max(value = 150, message = "Дистанция до моря не должна первышать 150")
    int distanceSea;
    CommunalType communal;
    @Min(value = 3, message = "Высота потолка не должна быть меньше 3")
    @Max(value = 5, message = "Высота потолка не должна быть превышать 5")
    int height;
    GasType gas;
    HeatingType heating;
    HeatingType sewerage;
    HeatingType waterSupply;
    @NotBlank(message = "Должно быть выбрано хотя бы одно")
    String advantages;
    @Pattern(regexp = "^[А-ЯЁA-Z][а-яёa-z]*$",message = "Должно содержать только буквы, начинаться и иметь только одну заглавную букву")
    @NotBlank(message = "Не должно быть пустым")
    @Size(min = 1,max = 255, message = "Размер типа оплаты должен быть меньше 255 и больше 1")
    String typePayment;
    @NotBlank(message = "Не должно быть пустым")
    @Pattern(regexp = "^[А-ЯЁA-Z][а-яёa-z]*$",message = "Должно содержать только буквы, начинаться и иметь только одну заглавную букву")
    @Size(min = 1,max = 255, message = "Размер назначения должен быть меньше 255 и больше 1")
    String appointment;
    @NotBlank(message = "Не должно быть пустым")
    @Pattern(regexp = "^[А-ЯЁA-Z][а-яёa-z]*$",message = "Должно содержать только буквы, начинаться и иметь только одну заглавную букву")
    @Size(min = 1,max = 255, message = "Размер типа суммы должен быть меньше 255 и больше 1")
    String sumContract;
    @NotBlank(message = "Не должно быть пустым")
    @Pattern(regexp = "^[А-ЯЁA-Z][а-яёa-z]*$",message = "Должно содержать только буквы, начинаться и иметь только одну заглавную букву")
    @Size(min = 1,max = 255, message = "Размер формализации должен быть меньше 255 и больше 1")
    String formalization;
    int contractor;
    List<NewsDTO> newsList;
    List<PhotoDTO> photoList;
    List<Integer> photoListId;
    @FileExtension(value = {"jpg", "png"}, message = "Формат некорректный (.jpg,.png)")
    List<MultipartFile> gallery;
    List<DocumentDTO> documents;
    List<Integer> documentListId;
    @FileExtension(value = {"pdf", "doc", "docx", "xlsx"}, message = "Формат некорректный (.pdf,.doc,.docx,.xlsx)")
    List<MultipartFile> documentsFiles;
    List<FrameDTO> frames;
}
