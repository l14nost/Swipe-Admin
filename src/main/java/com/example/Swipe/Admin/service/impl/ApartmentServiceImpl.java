package com.example.Swipe.Admin.service.impl;

import com.example.Swipe.Admin.dto.ApartmentDTO;
import com.example.Swipe.Admin.entity.Apartment;
import com.example.Swipe.Admin.entity.Frame;
import com.example.Swipe.Admin.entity.LCD;
import com.example.Swipe.Admin.entity.User;
import com.example.Swipe.Admin.mapper.ApartmentMapper;
import com.example.Swipe.Admin.repository.ApartmentRepo;
import com.example.Swipe.Admin.service.ApartmentService;
import com.example.Swipe.Admin.specification.ApartmentForFrameSpecification;
import com.example.Swipe.Admin.specification.ApartmentForLcdSpecification;
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
@Setter
@RequiredArgsConstructor
public class ApartmentServiceImpl implements ApartmentService {
    private Logger log = LoggerFactory.getLogger(ApartmentServiceImpl.class);
    private final ApartmentRepo apartmentRepo;
    private final FrameServiceImpl frameService;
    private final PhotosServiceImpl photosService;
    @Value("${upload.path}")
    private String upload;

    @Override
    public List<Apartment> findAll() {
        return apartmentRepo.findAll();
    }

    public void lcdIdToNull(Apartment apartment){
        apartment.setLcd(null);
        apartmentRepo.saveAndFlush(apartment);
    }


//    public Page<ApartmentDTO> findAllByFramePagination(Pageable pageable,int keyWord,String field){
//        if(keyWord != 0){
//            ApartmentForLcdSpecification apartmentForLcdSpecification = ApartmentForLcdSpecification.builder().isFrame(false).keyWord(keyWord).sort(field).build();
//            return apartmentRepo.findAll(apartmentForLcdSpecification,pageable).map(ApartmentMapper::apply);
//        }
//        ApartmentForLcdSpecification apartmentForLcdSpecification = ApartmentForLcdSpecification.builder().isFrame(false).sort(field).build();
//        return apartmentRepo.findAll(apartmentForLcdSpecification,pageable).map(ApartmentMapper::apply);
//    }
    public Page<ApartmentDTO> findAllByFramePagination(Pageable pageable,int keyWord,String field,int order){
        if(keyWord != 0){
            ApartmentForLcdSpecification apartmentForLcdSpecification = ApartmentForLcdSpecification.builder().isFrame(false).keyWord(keyWord).order(order).sort(field).build();
            return apartmentRepo.findAll(apartmentForLcdSpecification,pageable).map(ApartmentMapper::apply);
        }
        ApartmentForLcdSpecification apartmentForLcdSpecification = ApartmentForLcdSpecification.builder().isFrame(false).order(order).sort(field).build();
        return apartmentRepo.findAll(apartmentForLcdSpecification,pageable).map(ApartmentMapper::apply);
    }

//    public Page<ApartmentDTO> findAllForFramePagination(Frame frame,Pageable pageable, int keyWord, String field){
//        if (keyWord!=0){
//            ApartmentForFrameSpecification apartmentForFrameSpecification = ApartmentForFrameSpecification.builder().keyWord(keyWord).frame(frame).sort(field).build();
//            return apartmentRepo.findAll(apartmentForFrameSpecification,pageable).map(ApartmentMapper::apply);
//        }
//        ApartmentForFrameSpecification apartmentForFrameSpecification = ApartmentForFrameSpecification.builder().keyWord(keyWord).frame(frame).sort(field).build();
//        return apartmentRepo.findAll(apartmentForFrameSpecification,pageable).map(ApartmentMapper::apply);
//
//    }
    public Page<ApartmentDTO> findAllForFramePagination(Frame frame,Pageable pageable, int keyWord, String field,int order){
        if (keyWord!=0){
            ApartmentForFrameSpecification apartmentForFrameSpecification = ApartmentForFrameSpecification.builder().keyWord(keyWord).frame(frame).sort(field).order(order).build();
            return apartmentRepo.findAll(apartmentForFrameSpecification,pageable).map(ApartmentMapper::apply);
        }
        ApartmentForFrameSpecification apartmentForFrameSpecification = ApartmentForFrameSpecification.builder().keyWord(keyWord).frame(frame).sort(field).order(order).build();
        return apartmentRepo.findAll(apartmentForFrameSpecification,pageable).map(ApartmentMapper::apply);

    }
    public ApartmentDTO findByIdDTO(int id) {
        Optional<Apartment> apartment = apartmentRepo.findById(id);
        if(apartment.isPresent()){
            return ApartmentMapper.apply(apartment.get());
        }
        else {
            return null;
        }
    }
    @Override
    public Apartment findById(int id) {
        Optional<Apartment> apartment = apartmentRepo.findById(id);
        if(apartment.isPresent()){
            return apartment.get();
        }
        else {
            return null;
        }
    }
    public void saveDTO(ApartmentDTO apartmentDTO) {
        Apartment apartment = ApartmentMapper.toEntity(apartmentDTO);

        if (apartmentDTO.getFrame()!=0) {
            Frame frame = frameService.findById(apartmentDTO.getFrame());
            apartment.setFrame(frame);
            apartment.setLcd(frame.getLcd());
            apartment.setUser(frame.getLcd().getUser());
        }
        else {
            apartment.setLcd(LCD.builder().idLcd(apartmentDTO.getLcd()).build());
            apartment.setUser(User.builder().idUser(apartmentDTO.getUser()).build());
        }
        if (apartmentDTO.getFile()!=null) {
            if (!apartmentDTO.getFile().isEmpty()) {
                File uploadDirGallery = new File(upload);
                if (!uploadDirGallery.exists()) {
                    uploadDirGallery.mkdir();
                }
                String uuid = UUID.randomUUID().toString();
                String fileNameGallery = uuid + "-" + apartmentDTO.getFile().getOriginalFilename();
                String resultNameGallery = upload + "" + fileNameGallery;
                try {
                    apartmentDTO.getFile().transferTo(new File((resultNameGallery)));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                apartment.setMainPhoto("../uploads/" + fileNameGallery);
            } else {
                apartment.setMainPhoto("../admin/dist/img/default.jpg");
            }
        }
        apartmentRepo.save(apartment);
    }
    @Override
    public void saveEntity(Apartment apartment) {
        apartmentRepo.save(apartment);
    }

    @Override
    public void deleteById(int id) {
        Apartment apartment = findById(id);
        if (apartment.getMainPhoto()!=null) {
            if (!apartment.getMainPhoto().equals("../admin/dist/img/default.jpg")) {
                String fileNameDelete = apartment.getMainPhoto().substring(11, apartment.getMainPhoto().length());
                File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
                fileDelete.delete();
            }
        }
        if (apartment.getPhotoList()!=null) {
            for (int i = 0; i < apartment.getPhotoList().size(); i++) {
                if (!apartment.getPhotoList().get(i).getFileName().equals("../admin/dist/img/default.jpg")) {
                    String fileNameDelete = apartment.getPhotoList().get(i).getFileName().substring(11, apartment.getPhotoList().get(i).getFileName().length());
                    File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
                    fileDelete.delete();
                }
            }
        }
        apartmentRepo.deleteById(id);
    }

    @Override
    public void updateEntity(Apartment apartment, int id) {
        Optional<Apartment> apartmentOptional = apartmentRepo.findById(id);
        if(apartmentOptional.isPresent()){
            Apartment apartmentUpdate = apartmentOptional.get();
            if(apartment.getPhotoList()!=null){
                apartmentUpdate.setPhotoList(apartment.getPhotoList());
            }
            if(apartment.getCommission()!=null){
                apartmentUpdate.setCommission(apartment.getCommission());
            }
            if(apartment.getCalculation()!=null){
                apartmentUpdate.setCalculation(apartment.getCalculation());
            }
            if(apartment.getBalconyType()!=null){
                apartmentUpdate.setBalconyType(apartment.getBalconyType());
            }
            if(apartment.getLcd()!=null){
                apartmentUpdate.setLcd(apartment.getLcd());
            }
            if(apartment.getDescription()!=null){
                apartmentUpdate.setDescription(apartment.getDescription());
            }
            if(apartment.getFoundingDocument()!=null){
                apartmentUpdate.setFoundingDocument(apartment.getFoundingDocument());
            }
            if(apartment.getCommunicationType()!=null){
                apartmentUpdate.setCommunicationType(apartment.getCommunicationType());
            }
            if(apartment.getCountRoom()!=null){
                apartmentUpdate.setCountRoom(apartment.getCountRoom());
            }
            if(apartment.getMainPhoto()!=null){
                apartmentUpdate.setMainPhoto(apartment.getMainPhoto());
            }
            if(apartment.getTotalArea()!=0){
                apartmentUpdate.setTotalArea(apartment.getTotalArea());
            }
            if(apartment.getKitchenArea()!=0){
                apartmentUpdate.setKitchenArea(apartment.getKitchenArea());
            }
            if(apartment.getNumber()!=0){
                apartmentUpdate.setNumber(apartment.getNumber());
            }
            if(apartment.getLayout()!=null){
                apartmentUpdate.setLayout(apartment.getLayout());
            }
            if(apartment.getPrice()!=0){
                apartmentUpdate.setPrice(apartment.getPrice());
            }
            if(apartment.getState()!=null){
                apartmentUpdate.setState(apartment.getState());
            }
            if(apartment.getType()!=null){
                apartmentUpdate.setType(apartment.getType());
            }
            if(apartment.getUser()!=null){
                apartmentUpdate.setUser(apartment.getUser());
            }
            if (apartment.getAddress()!=null){
                apartmentUpdate.setAddress(apartment.getAddress());
            }
            apartmentRepo.saveAndFlush(apartmentUpdate);
        }
    }

    public void updateDTO(ApartmentDTO apartmentDTO, int id) throws IOException {
        Apartment apartment = ApartmentMapper.toEntity(apartmentDTO);
        if (apartmentDTO.getFile()!=null) {
            if (!apartmentDTO.getFile().isEmpty()) {
                System.out.println("+");
                File uploadDirGallery = new File(upload);
                if (!uploadDirGallery.exists()) {
                    uploadDirGallery.mkdir();
                }
                String uuid = UUID.randomUUID().toString();
                String fileNameGallery = uuid + "-" + apartmentDTO.getFile().getOriginalFilename();
                String resultNameGallery = upload + "" + fileNameGallery;
                try {
                    apartmentDTO.getFile().transferTo(new File((resultNameGallery)));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                apartment.setMainPhoto("../uploads/" + fileNameGallery);
            }
        }
        Optional<Apartment> apartmentOptional = apartmentRepo.findById(id);
        if(apartmentOptional.isPresent()){
            Apartment apartmentUpdate = apartmentOptional.get();
            if(apartmentUpdate.getPhotoList()!=null){
                if (apartmentDTO.getGalleryPhoto()!=null) {
                    for (int i = 0; i < apartmentUpdate.getPhotoList().size(); i++) {
                        if (!apartmentDTO.getGalleryPhoto().get(i).isEmpty()) {
                            File uploadDirGallery = new File(upload);
                            if (!uploadDirGallery.exists()) {
                                uploadDirGallery.mkdir();
                            }
                            String uuid = UUID.randomUUID().toString();
                            String fileNameGallery = uuid + "-" + apartmentDTO.getGalleryPhoto().get(i).getOriginalFilename();
                            String resultNameGallery = upload + "" + fileNameGallery;
                            apartmentDTO.getGalleryPhoto().get(i).transferTo(new File((resultNameGallery)));
                            if (!apartmentUpdate.getPhotoList().get(i).getFileName().equals("../admin/dist/img/default.jpg")) {
                                String fileNameDelete = apartmentUpdate.getPhotoList().get(i).getFileName().substring(11, apartmentUpdate.getPhotoList().get(i).getFileName().length());
                                File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
                                fileDelete.delete();
                            }
                            apartmentUpdate.getPhotoList().get(i).setFileName("../uploads/" + fileNameGallery);
                            photosService.updateEntity(apartmentUpdate.getPhotoList().get(i), apartmentUpdate.getPhotoList().get(i).getIdPhotos());
                        }
                    }
                }
            }
            if (apartmentDTO.getFile()!=null) {
                if (!apartmentUpdate.getMainPhoto().equals("../admin/dist/img/default.jpg") && !apartmentDTO.getFile().isEmpty()) {
                    String fileNameDelete = apartmentUpdate.getMainPhoto().substring(11, apartmentUpdate.getMainPhoto().length());
                    File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
                    fileDelete.delete();
                }
            }

            if(apartment.getCommission()!=null){
                apartmentUpdate.setCommission(apartment.getCommission());
            }
            if(apartment.getCalculation()!=null){
                apartmentUpdate.setCalculation(apartment.getCalculation());
            }
            if(apartment.getBalconyType()!=null){
                apartmentUpdate.setBalconyType(apartment.getBalconyType());
            }
            if (apartment.getAddress()!=null){
                apartmentUpdate.setAddress(apartment.getAddress());
            }
            if(apartment.getDescription()!=null){
                apartmentUpdate.setDescription(apartment.getDescription());
            }
            if(apartment.getFoundingDocument()!=null){
                apartmentUpdate.setFoundingDocument(apartment.getFoundingDocument());
            }
            if(apartment.getCommunicationType()!=null){
                apartmentUpdate.setCommunicationType(apartment.getCommunicationType());
            }
            if(apartment.getCountRoom()!=null){
                apartmentUpdate.setCountRoom(apartment.getCountRoom());
            }
            if(apartment.getTotalArea()!=0){
                apartmentUpdate.setTotalArea(apartment.getTotalArea());
            }
            if(apartment.getKitchenArea()!=0){
                apartmentUpdate.setKitchenArea(apartment.getKitchenArea());
            }
            if(apartment.getNumber()!=0){
                apartmentUpdate.setNumber(apartment.getNumber());
            }
            if(apartment.getLayout()!=null){
                apartmentUpdate.setLayout(apartment.getLayout());
            }
            if(apartment.getPrice()!=0){
                apartmentUpdate.setPrice(apartment.getPrice());
            }
            if(apartment.getMainPhoto()!=null){
                apartmentUpdate.setMainPhoto(apartment.getMainPhoto());
            }
            if(apartment.getState()!=null){
                apartmentUpdate.setState(apartment.getState());
            }
            if(apartment.getHeatingType()!=null){
                apartmentUpdate.setHeatingType(apartment.getHeatingType());
            }
            if(apartment.getType()!=null){
                apartmentUpdate.setType(apartment.getType());
            }

            if (apartmentDTO.getLcd()!=0){
                apartmentUpdate.setLcd(LCD.builder().idLcd(apartmentDTO.getLcd()).build());
            }
            if (apartmentDTO.getUser()!=0){
                apartmentUpdate.setUser(User.builder().idUser(apartmentDTO.getUser()).build());
            }
            apartmentRepo.saveAndFlush(apartmentUpdate);
        }
    }


    public int count() {
        return apartmentRepo.countByFrameIsNull();
    }
    public int countNotNull() {
        return apartmentRepo.countByFrameIsNotNull();
    }
    public int count(Frame frame) {
        return apartmentRepo.countByFrame(frame);
    }
}
