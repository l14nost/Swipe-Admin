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
public class ApartmentServiceImpl implements ApartmentService {
    private final ApartmentRepo apartmentRepo;
    private final LCDServiceImpl lcdService;
    private final FrameServiceImpl frameService;
    private final UserServiceImpl userService;
    private final PhotosServiceImpl photosService;
    @Value("${upload.path}")
    private String upload;

    @Override
    public List<Apartment> findAll() {
        return apartmentRepo.findAll();
    }

    public List<Apartment> findAllByFrame(){
        return apartmentRepo.findAllByFrameIsNull();
    }

    public Page<Apartment> findAllByFramePagination(Pageable pageable,int keyWord){
        if(keyWord != 0){
            ApartmentForLcdSpecification apartmentForLcdSpecification = ApartmentForLcdSpecification.builder().keyWord(keyWord).build();
            return apartmentRepo.findAll(apartmentForLcdSpecification,pageable);
        }
        return apartmentRepo.findAllByFrameIsNull(pageable);
    }

    public Page<Apartment> findAllForFramePagination(Frame frame,Pageable pageable, int keyWord){
        if (keyWord!=0){
            ApartmentForFrameSpecification apartmentForFrameSpecification = ApartmentForFrameSpecification.builder().keyWord(keyWord).frame(frame).build();
            return apartmentRepo.findAll(apartmentForFrameSpecification,pageable);
        }
        return apartmentRepo.findAllByFrame(frame,pageable);
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
            return Apartment.builder().build();
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
            apartment.setLcd(lcdService.findById(apartmentDTO.getLcd()));
            apartment.setUser(userService.findById(apartmentDTO.getUser()));
        }
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
        else {
            apartment.setMainPhoto("../admin/dist/img/default.jpg");
        }
        apartmentRepo.save(apartment);
    }
    @Override
    public void saveEntity(Apartment apartment) {
        apartmentRepo.save(apartment);
    }

    @Override
    public void deleteById(int id) {
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
            if(apartment.getMainPhoto()!=null){
                apartmentUpdate.setMainPhoto(apartment.getMainPhoto());
            }
            if(apartment.getAddress()!=null){
                apartmentUpdate.setAddress(apartment.getAddress());
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
            if(apartment.getMainPhoto()!=null){
                apartmentUpdate.setMainPhoto(apartment.getMainPhoto());
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

            apartmentRepo.saveAndFlush(apartmentUpdate);
        }
    }

    public void updateDTO(ApartmentDTO apartmentDTO, int id) throws IOException {
        Frame frame = frameService.findById(apartmentDTO.getFrame());
        LCD lcd = lcdService.findById(apartmentDTO.getLcd());
        User user = userService.findById(apartmentDTO.getUser());
        Apartment apartment = ApartmentMapper.toEntity(apartmentDTO);
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
        Optional<Apartment> apartmentOptional = apartmentRepo.findById(id);
        if(apartmentOptional.isPresent()){
            Apartment apartmentUpdate = apartmentOptional.get();
            if(apartmentUpdate.getPhotoList()!=null){
                for(int i = 0; i<apartmentUpdate.getPhotoList().size(); i++){
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
                        photosService.updateEntity(apartmentUpdate.getPhotoList().get(i),apartmentUpdate.getPhotoList().get(i).getIdPhotos() );
                    }
                }
            }
            if(!apartmentUpdate.getMainPhoto().equals("../admin/dist/img/default.jpg")&&!apartmentDTO.getFile().isEmpty()){
                String fileNameDelete = apartmentUpdate.getMainPhoto().substring(11, apartmentUpdate.getMainPhoto().length());
                File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
                fileDelete.delete();
            }
            if(apartment.getAddress()!=null){
                apartmentUpdate.setAddress(apartment.getAddress());
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
            if (apartmentDTO.getFrame()!=0){
                apartmentUpdate.setFrame(frameService.findById(apartmentDTO.getFrame()));
            }
            if (apartmentDTO.getLcd()!=0){
                apartmentUpdate.setLcd(lcdService.findById(apartmentDTO.getLcd()));
            }
            if (apartmentDTO.getUser()!=0){
                apartmentUpdate.setUser(userService.findById(apartmentDTO.getUser()));
            }
            apartmentRepo.saveAndFlush(apartmentUpdate);
        }
    }


}
