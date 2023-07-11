package com.example.Swipe.Admin.service.impl;

import com.example.Swipe.Admin.dto.ApartmentDTO;
import com.example.Swipe.Admin.dto.FrameDTO;
import com.example.Swipe.Admin.entity.Frame;
import com.example.Swipe.Admin.mapper.ApartmentMapper;
import com.example.Swipe.Admin.mapper.FrameMapper;
import com.example.Swipe.Admin.repository.FrameRepo;
import com.example.Swipe.Admin.service.FrameService;
import com.example.Swipe.Admin.specification.ApartmentForLcdSpecification;
import com.example.Swipe.Admin.specification.FrameSpecification;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Setter
public class FrameServiceImpl implements FrameService {
    private Logger log = LoggerFactory.getLogger(FrameServiceImpl.class);
    private final FrameRepo frameRepo;
    @Value("${upload.path}")
    private String upload;


    @Override
    public List<Frame> findAll() {
        return frameRepo.findAll();
    }

    @Override
    public Frame findById(int id) {
        Optional<Frame> frame = frameRepo.findById(id);
        if(frame.isPresent()){
            return frame.get();
        }
        else {
            return null;
        }
    }

    @Override
    public void saveEntity(Frame frame) {
        frameRepo.save(frame);
    }

    @Override
    public void deleteById(int id) {
        Frame frame = findById(id);
        if (frame.getApartmentList().size()>0) {
            for (int i = 0; i < frame.getApartmentList().size(); i++) {
                if (!frame.getApartmentList().get(i).getMainPhoto().equals("../admin/dist/img/default.jpg")) {
                    String fileNameDelete = frame.getApartmentList().get(i).getMainPhoto().substring(11, frame.getApartmentList().get(i).getMainPhoto().length());
                    File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
                    fileDelete.delete();
                }
                if (frame.getApartmentList().get(i).getPhotoList().size()>0) {
                    for (int j = 0; j < frame.getApartmentList().get(i).getPhotoList().size(); j++) {
                        if (!frame.getApartmentList().get(i).getPhotoList().get(j).getFileName().equals("../admin/dist/img/default.jpg")) {
                            String fileNameDelete = frame.getApartmentList().get(i).getPhotoList().get(j).getFileName().substring(11, frame.getApartmentList().get(i).getPhotoList().get(j).getFileName().length());
                            File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
                            fileDelete.delete();
                        }

                    }
                }
            }
        }
        frameRepo.deleteById(id);
    }

    @Override
    public void updateEntity(Frame frame, int id) {
        Optional<Frame> frameOptional = frameRepo.findById(id);
        if(frameOptional.isPresent()){
            Frame frameUpdate = frameOptional.get();
            if(frame.getApartmentList()!=null){
                frameUpdate.setApartmentList(frame.getApartmentList());
            }
            if (frame.getLcd()!=null){
                frameUpdate.setLcd(frame.getLcd());
            }
            if(frame.getNum()!=0){
                frameUpdate.setNum(frame.getNum());
            }
            frameRepo.saveAndFlush(frameUpdate);
        }
    }

//    public Page<FrameDTO> pagination(Pageable pageable, int keyWord, String field, int order){
//        if(keyWord != 0){
//            FrameSpecification apartmentForLcdSpecification = FrameSpecification.builder().keyWord(keyWord).order(order).sort(field).build();
//            return frameRepo.findAll(apartmentForLcdSpecification,pageable).map(FrameMapper::apply);
//        }
//        FrameSpecification apartmentForLcdSpecification = FrameSpecification.builder().order(order).sort(field).build();
//        return frameRepo.findAll(apartmentForLcdSpecification,pageable).map(FrameMapper::apply);
//    }
//
//    public int count() {
//        return (int) frameRepo.count();
//    }
}
