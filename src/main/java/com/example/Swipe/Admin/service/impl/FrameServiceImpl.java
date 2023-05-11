package com.example.Swipe.Admin.service.impl;

import com.example.Swipe.Admin.entity.Frame;
import com.example.Swipe.Admin.repository.FrameRepo;
import com.example.Swipe.Admin.service.FrameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FrameServiceImpl implements FrameService {
    private final FrameRepo frameRepo;


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
            return Frame.builder().build();
        }
    }

    @Override
    public void saveEntity(Frame frame) {
        frameRepo.save(frame);
    }

    @Override
    public void deleteById(int id) {
        frameRepo.deleteById(id);
    }

    @Override
    public void updateEntity(Frame frame, int id) {
//        Optional<Photos> photosOptional = photosRepo.findById(id);
//        if(photosOptional.isPresent()){
//            Photos photosUpdate = photosOptional.get();
//            if(photos.getApartment()!=null){
//                photosUpdate.setApartment(photos.getApartment());
//            }
//            if (photos.getLcd()!=null){
//                photosUpdate.setLcd(photos.getLcd());
//            }
//            if(photos.getFileName()!=null){
//                photosUpdate.setFileName(photos.getFileName());
//            }
//            photosRepo.saveAndFlush(photosUpdate);
//        }
    }
}
