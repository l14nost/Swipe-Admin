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
}
