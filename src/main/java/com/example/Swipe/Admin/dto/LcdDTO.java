package com.example.Swipe.Admin.dto;

import com.example.Swipe.Admin.entity.News;
import com.example.Swipe.Admin.enums.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
public class LcdDTO {
    int idLcd;
    MultipartFile mainPhoto;
    String name;
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
    ClientDTO clientDTO;
    List<NewsDTO> newsList;
    List<PhotoDTO> photoList;
    List<DocumentDTO> documents;
    List<FrameDTO> frames;
}
