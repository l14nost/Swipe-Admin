package com.example.Swipe.Admin.service.impl;

import com.example.Swipe.Admin.entity.Contractor;
import com.example.Swipe.Admin.entity.Documents;
import com.example.Swipe.Admin.entity.Photos;
import com.example.Swipe.Admin.repository.PhotosRepo;
import com.example.Swipe.Admin.service.PhotosService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotosServiceImpl implements PhotosService {
    private final PhotosRepo photosRepo;

    public PhotosServiceImpl(PhotosRepo photosRepo) {
        this.photosRepo = photosRepo;
    }

    @Override
    public List<Photos> findAll() {
        return photosRepo.findAll();
    }

    @Override
    public Photos findById(int id) {
        Optional<Photos> photos = photosRepo.findById(id);
        if(photos.isPresent()){
            return photos.get();
        }
        else {
            return Photos.builder().build();
        }
    }

    @Override
    public void saveEntity(Photos photos) {
        photosRepo.save(photos);
    }

    @Override
    public void deleteById(int id) {
        photosRepo.deleteById(id);
    }

    @Override
    public void updateEntity(Photos photos, int id) {
        Optional<Photos> photosOptional = photosRepo.findById(id);
        if(photosOptional.isPresent()){
            Photos photosUpdate = photosOptional.get();
            if(photos.getApartment()!=null){
                photosUpdate.setApartment(photos.getApartment());
            }
            if (photos.getLcd()!=null){
                photosUpdate.setLcd(photos.getLcd());
            }
            if(photos.getFileName()!=null){
                photosUpdate.setFileName(photos.getFileName());
            }
            photosRepo.saveAndFlush(photosUpdate);
        }
    }
}
