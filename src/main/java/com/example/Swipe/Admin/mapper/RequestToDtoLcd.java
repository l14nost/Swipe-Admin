package com.example.Swipe.Admin.mapper;

import com.example.Swipe.Admin.dto.LcdDTO;
import com.example.Swipe.Admin.dto.RequestLcdDto;
import com.example.Swipe.Admin.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;

public class RequestToDtoLcd {
    public static LcdDTO toDto(LcdDTO request, LcdDTO lcdDTO){
        lcdDTO.setName(request.getName());
        lcdDTO.setDescription(request.getDescription());
        lcdDTO.setStatus(request.getStatus());
        lcdDTO.setLcdClass(request.getLcdClass());
        lcdDTO.setTechnology(request.getTechnology());
        lcdDTO.setTerritory(request.getTerritory());
        lcdDTO.setDistanceSea(request.getDistanceSea());
        lcdDTO.setCommunal(request.getCommunal());
        lcdDTO.setHeight(request.getHeight());
        lcdDTO.setGas(request.getGas());
        lcdDTO.setHeating(request.getHeating());
        lcdDTO.setSewerage(request.getSewerage());
        lcdDTO.setWaterSupply(request.getWaterSupply());
        lcdDTO.setAdvantages(request.getAdvantages());
        lcdDTO.setTypePayment(request.getTypePayment());
        lcdDTO.setAppointment(request.getAppointment());
        lcdDTO.setSumContract(request.getSumContract());
        lcdDTO.setFormalization(request.getFormalization());
        lcdDTO.setType(request.getType());
        if (!request.getFile().isEmpty()){
            lcdDTO.setMainPhoto("../admin/dist/img/default.jpg");
        }
        for (int i = 0; i < request.getGallery().size();i++){
            if (!request.getGallery().get(i).isEmpty()){
                lcdDTO.getPhotoList().get(i).setFileName("../admin/dist/img/default.jpg");
            }
        }
        return lcdDTO;
    }
    public static LcdDTO toDtoResult(LcdDTO request, LcdDTO lcdDTO){
        System.out.println(request.getAdvantages()+"-");
//       request.setClientDTO(lcdDTO.getClientDTO());
       request.setPhotoList(lcdDTO.getPhotoList());
       request.setMainPhoto(lcdDTO.getMainPhoto());
       request.setDocuments(lcdDTO.getDocuments());
       request.setFrames(lcdDTO.getFrames());
       request.setNewsList(lcdDTO.getNewsList());
        return request;
    }
}
