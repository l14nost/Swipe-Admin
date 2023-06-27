package com.example.Swipe.Admin.service.impl;

import com.example.Swipe.Admin.dto.LcdDTO;
import com.example.Swipe.Admin.entity.LCD;
import com.example.Swipe.Admin.entity.User;
import com.example.Swipe.Admin.mapper.LcdMapper;
import com.example.Swipe.Admin.repository.LCDRepo;
import com.example.Swipe.Admin.service.LCDService;
import com.example.Swipe.Admin.specification.LcdSpecification;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Setter
public class LCDServiceImpl implements LCDService {
    private Logger log = LoggerFactory.getLogger(LCDServiceImpl.class);
    private final LCDRepo lcdRepo;
    @Value("${upload.path}")
    private String upload;
    private final PhotosServiceImpl photosService;
    private final DocumentsServiceImpl documentsService;
    private final UserServiceImpl userService;

    public Page<LcdDTO> findAllPagination(Pageable pageable,String keyWord,String sort){
        if(!keyWord.equals("null")){
            LcdSpecification lcdSpecification = LcdSpecification.builder().keyWord(keyWord).sort(sort).build();
            return lcdRepo.findAll(lcdSpecification,pageable).map(LcdMapper::apply);
        }
        LcdSpecification lcdSpecification = LcdSpecification.builder().sort(sort).build();
        return lcdRepo.findAll(lcdSpecification,pageable).map(LcdMapper::apply);
    }

    public int count(){
        return (int) lcdRepo.count();
    }

    @Override
    public List<LCD> findAll() {
        return lcdRepo.findAll();
    }
    public LcdDTO findByIdDTO(int id) {
        Optional<LCD> lcd = lcdRepo.findById(id);
        if(lcd.isPresent()){
            return LcdMapper.apply(lcd.get());
        }
        else {
            return null;
        }
    }
    @Override
    public LCD findById(int id) {
        Optional<LCD> lcd = lcdRepo.findById(id);
        if(lcd.isPresent()){
            return lcd.get();
        }
        else {
            return null;
        }
    }
    public void saveDTO(LcdDTO lcdDTO) {
        LCD lcd = LcdMapper.toEntity(lcdDTO);
        lcd.setUser(userService.findById(lcdDTO.getContractor()));
        if (lcdDTO.getFile()!=null) {
            if (!lcdDTO.getFile().isEmpty()) {
                File uploadDirGallery = new File(upload);
                if (!uploadDirGallery.exists()) {
                    uploadDirGallery.mkdir();
                }
                String uuid = UUID.randomUUID().toString();
                String fileNameGallery = uuid + "-" + lcdDTO.getFile().getOriginalFilename();
                String resultNameGallery = upload + "" + fileNameGallery;
                try {
                    lcdDTO.getFile().transferTo(new File((resultNameGallery)));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                lcd.setMainPhoto("../uploads/" + fileNameGallery);
            } else lcd.setMainPhoto("../admin/dist/img/default.jpg");
        }else lcd.setMainPhoto("../admin/dist/img/default.jpg");
        lcdRepo.save(lcd);
    }
    @Override
    public void saveEntity(LCD lcd) {
        lcdRepo.save(lcd);
    }

    @Override
    public void deleteById(int id) {
        LCD lcd = findById(id);
        if (!lcd.getMainPhoto().equals("../admin/dist/img/default.jpg")) {
            String fileNameDelete = lcd.getMainPhoto().substring(11, lcd.getMainPhoto().length());
            File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
            fileDelete.delete();
        }
        for(int i = 0; i< lcd.getPhotoList().size();i++){
            if (!lcd.getPhotoList().get(i).getFileName().equals("../admin/dist/img/default.jpg")) {
                String fileNameDelete = lcd.getPhotoList().get(i).getFileName().substring(11, lcd.getPhotoList().get(i).getFileName().length());
                File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
                fileDelete.delete();
            }
        }
        for(int i = 0; i< lcd.getDocuments().size();i++){
            if (!lcd.getDocuments().get(i).getFileName().equals("../admin/dist/img/default.jpg")) {
                String fileNameDelete = lcd.getDocuments().get(i).getFileName().substring(11, lcd.getDocuments().get(i).getFileName().length());
                File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
                fileDelete.delete();
            }
        }

        for (int i = 0; i<lcd.getFrames().size();i++){
            for(int j = 0;j<lcd.getFrames().get(i).getApartmentList().size();j++){
                if (!lcd.getFrames().get(i).getApartmentList().get(j).getMainPhoto().equals("../admin/dist/img/default.jpg")) {
                    String fileNameDelete = lcd.getFrames().get(i).getApartmentList().get(j).getMainPhoto().substring(11, lcd.getFrames().get(i).getApartmentList().get(j).getMainPhoto().length());
                    File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
                    fileDelete.delete();
                }
                for (int k = 0; k < lcd.getFrames().get(i).getApartmentList().get(j).getPhotoList().size(); k++) {
                    if (!lcd.getFrames().get(i).getApartmentList().get(j).getPhotoList().get(k).getFileName().equals("../admin/dist/img/default.jpg")) {
                        String fileNameDelete = lcd.getFrames().get(i).getApartmentList().get(j).getPhotoList().get(k).getFileName().substring(11, lcd.getFrames().get(i).getApartmentList().get(j).getPhotoList().get(k).getFileName().length());
                        File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
                        fileDelete.delete();
                    }

                }
            }
        }
        lcdRepo.deleteById(id);
    }

    @Override
    public void updateEntity(LCD lcd, int id) {
        Optional<LCD> lcdOptional = lcdRepo.findById(id);
        if(lcdOptional.isPresent()){
            LCD lcdUpdate = lcdOptional.get();
            if(lcd.getPhotoList()!=null){
                lcdUpdate.setPhotoList(lcd.getPhotoList());
            }
            if(lcd.getMainPhoto()!=null){
                lcdUpdate.setMainPhoto(lcd.getMainPhoto());
            }
            if(lcd.getAdvantages()!=null){
                lcdUpdate.setAdvantages(lcd.getAdvantages());
            }
            if(lcd.getAppointment() !=null){
                lcdUpdate.setAppointment(lcd.getAppointment());
            }
            if(lcd.getCommunal()!=null){
                lcdUpdate.setCommunal(lcd.getCommunal());
            }
            if(lcd.getGas()!=null){
                lcdUpdate.setGas(lcd.getGas());
            }
            if(lcd.getHeating()!=null){
                lcdUpdate.setHeating(lcd.getHeating());
            }
            if(lcd.getName()!=null){
                lcdUpdate.setName(lcd.getName());
            }
            if(lcd.getSewerage()!=null){
                lcdUpdate.setSewerage(lcd.getSewerage());
            }
            if(lcd.getStatus()!=null){
                lcdUpdate.setStatus(lcd.getStatus());
            }
            if(lcd.getTechnology()!=null){
                lcdUpdate.setTechnology(lcd.getTechnology());
            }
            if(lcd.getMainPhoto()!=null){
                lcdUpdate.setMainPhoto(lcd.getMainPhoto());
            }
            if(lcd.getDistanceSea()!=0){
                lcdUpdate.setDistanceSea(lcd.getDistanceSea());
            }
            if(lcd.getHeight()!=0){
                lcdUpdate.setHeight(lcd.getHeight());
            }
            if(lcd.getSumContract()!=null){
                lcdUpdate.setSumContract(lcd.getSumContract());
            }
            if(lcd.getTerritory()!=null){
                lcdUpdate.setTerritory(lcd.getTerritory());
            }
            if(lcd.getType()!=null){
                lcdUpdate.setType(lcd.getType());
            }
            if(lcd.getTypePayment()!=null){
                lcdUpdate.setTypePayment(lcd.getTypePayment());
            }
            if(lcd.getWaterSupply()!=null){
                lcdUpdate.setWaterSupply(lcd.getWaterSupply());
            }
            if(lcd.getDescription()!=null){
                lcdUpdate.setDescription(lcd.getDescription());
            }
            if(lcd.getLcdClass()!=null){
                lcdUpdate.setLcdClass(lcd.getLcdClass());
            }
            if (lcd.getAddress()!=null){
                lcdUpdate.setAddress(lcd.getAddress());
            }
            if(lcd.getUser()!=null){
                lcdUpdate.setUser(lcd.getUser());
            }
            if(lcd.getFormalization()!=null){
                lcdUpdate.setFormalization(lcd.getFormalization());
            }
            lcdRepo.saveAndFlush(lcdUpdate);
        }

    }

    public void updateDTO(LcdDTO lcdDTO, int id) {
        User user = User.builder().idUser(lcdDTO.getContractor()).build();
        LCD lcd = LcdMapper.toEntity(lcdDTO);
        if (lcdDTO.getFile()!=null) {
            if (!lcdDTO.getFile().isEmpty()) {
                System.out.println("+");
                File uploadDirGallery = new File(upload);
                if (!uploadDirGallery.exists()) {
                    uploadDirGallery.mkdir();
                }
                String uuid = UUID.randomUUID().toString();
                String fileNameGallery = uuid + "-" + lcdDTO.getFile().getOriginalFilename();
                String resultNameGallery = upload + "" + fileNameGallery;
                try {
                    lcdDTO.getFile().transferTo(new File((resultNameGallery)));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                lcd.setMainPhoto("../uploads/" + fileNameGallery);
            }
        }

        Optional<LCD> lcdOptional = lcdRepo.findById(id);
        if(lcdOptional.isPresent()){
            LCD lcdUpdate = lcdOptional.get();
            if(lcdUpdate.getPhotoList()!=null){
                if (lcdDTO.getGallery()!=null) {
                for(int i = 0; i<lcdUpdate.getPhotoList().size(); i++){

                        if (!lcdDTO.getGallery().get(i).isEmpty()) {
                            File uploadDirGallery = new File(upload);
                            if (!uploadDirGallery.exists()) {
                                uploadDirGallery.mkdir();
                            }
                            String uuid = UUID.randomUUID().toString();
                            String fileNameGallery = uuid + "-" + lcdDTO.getGallery().get(i).getOriginalFilename();
                            String resultNameGallery = upload + "" + fileNameGallery;
                            try {
                                lcdDTO.getGallery().get(i).transferTo(new File((resultNameGallery)));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            if (!lcdUpdate.getPhotoList().get(i).getFileName().equals("../admin/dist/img/default.jpg")) {
                                String fileNameDelete = lcdUpdate.getPhotoList().get(i).getFileName().substring(11, lcdUpdate.getPhotoList().get(i).getFileName().length());
                                File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
                                fileDelete.delete();
                            }
                            lcdUpdate.getPhotoList().get(i).setFileName("../uploads/" + fileNameGallery);
                            photosService.updateEntity(lcdUpdate.getPhotoList().get(i), lcdUpdate.getPhotoList().get(i).getIdPhotos());
                        }
                    }
                }
            }
            if (lcdUpdate.getDocuments()!=null){
                if (lcdDTO.getDocumentsFiles()!=null) {
                    for (int i = 0; i < lcdUpdate.getDocuments().size(); i++) {
                        if (!lcdDTO.getDocumentsFiles().get(i).isEmpty()) {
                            File uploadDirGallery = new File(upload);
                            if (!uploadDirGallery.exists()) {
                                uploadDirGallery.mkdir();
                            }
                            String uuid = UUID.randomUUID().toString();
                            String fileNameGallery = uuid + "-" + lcdDTO.getDocumentsFiles().get(i).getOriginalFilename();
                            lcdUpdate.getDocuments().get(i).setName(lcdDTO.getDocumentsFiles().get(i).getOriginalFilename());
                            String resultNameGallery = upload + "" + fileNameGallery;
                            try {
                                lcdDTO.getDocumentsFiles().get(i).transferTo(new File((resultNameGallery)));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            if (!lcdUpdate.getDocuments().get(i).getFileName().equals("../admin/dist/img/document.jpg")) {
                                String fileNameDelete = lcdUpdate.getDocuments().get(i).getFileName().substring(11, lcdUpdate.getDocuments().get(i).getFileName().length());
                                File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
                                fileDelete.delete();
                            }
                            lcdUpdate.getDocuments().get(i).setFileName("../uploads/" + fileNameGallery);
                            documentsService.updateEntity(lcdUpdate.getDocuments().get(i), lcdUpdate.getDocuments().get(i).getIdDocuments());
                        }
                    }
                }
            }
            if (lcdUpdate.getMainPhoto()!=null) {
                if (lcdDTO.getFile()!=null) {
                    if (!lcdUpdate.getMainPhoto().equals("../admin/dist/img/default.jpg") && !lcdDTO.getFile().isEmpty()) {
                        String fileNameDelete = lcdUpdate.getMainPhoto().substring(11, lcdUpdate.getMainPhoto().length());
                        File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
                        fileDelete.delete();
                    }
                }
            }
            if(lcd.getAdvantages()!=null){
                lcdUpdate.setAdvantages(lcd.getAdvantages());
            }
            if (lcd.getAddress()!=null){
                lcdUpdate.setAddress(lcd.getAddress());
            }
            if(lcd.getAppointment() !=null){
                lcdUpdate.setAppointment(lcd.getAppointment());
            }
            if(lcd.getCommunal()!=null){
                lcdUpdate.setCommunal(lcd.getCommunal());
            }
            if(lcd.getGas()!=null){
                lcdUpdate.setGas(lcd.getGas());
            }
            if(lcd.getHeating()!=null){
                lcdUpdate.setHeating(lcd.getHeating());
            }
            if(lcd.getName()!=null){
                lcdUpdate.setName(lcd.getName());
            }
            if(lcd.getSewerage()!=null){
                lcdUpdate.setSewerage(lcd.getSewerage());
            }
            if(lcd.getStatus()!=null){
                lcdUpdate.setStatus(lcd.getStatus());
            }
            if(lcd.getTechnology()!=null){
                lcdUpdate.setTechnology(lcd.getTechnology());
            }
            if (lcd.getMainPhoto()!=null){
                lcdUpdate.setMainPhoto(lcd.getMainPhoto());
            }
            if(lcd.getDistanceSea()!=0){
                lcdUpdate.setDistanceSea(lcd.getDistanceSea());
            }
            if(lcd.getHeight()!=0){
                lcdUpdate.setHeight(lcd.getHeight());
            }
            if(lcd.getSumContract()!=null){
                lcdUpdate.setSumContract(lcd.getSumContract());
            }
            if(lcd.getTerritory()!=null){
                lcdUpdate.setTerritory(lcd.getTerritory());
            }
            if(lcd.getType()!=null){
                lcdUpdate.setType(lcd.getType());
            }
            if(lcd.getTypePayment()!=null){
                lcdUpdate.setTypePayment(lcd.getTypePayment());
            }
            if(lcd.getWaterSupply()!=null){
                lcdUpdate.setWaterSupply(lcd.getWaterSupply());
            }
            if(lcd.getDescription()!=null){
                lcdUpdate.setDescription(lcd.getDescription());
            }
            if(lcd.getLcdClass()!=null){
                lcdUpdate.setLcdClass(lcd.getLcdClass());
            }
            if(lcd.getFormalization()!=null){
                lcdUpdate.setFormalization(lcd.getFormalization());
            }
            lcdUpdate.setUser(user);

            lcdRepo.saveAndFlush(lcdUpdate);
        }
    }


}
