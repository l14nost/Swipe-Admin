package com.example.Swipe.Admin.service.impl;

import com.example.Swipe.Admin.entity.Photo;
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
    public List<Photo> findAll() {
        return photosRepo.findAll();
    }

    @Override
    public Photo findById(int id) {
        Optional<Photo> photos = photosRepo.findById(id);
        if(photos.isPresent()){
            return photos.get();
        }
        else {
            return Photo.builder().build();
        }
    }

    @Override
    public void saveEntity(Photo photo) {
        photosRepo.save(photo);
    }

    @Override
    public void deleteById(int id) {
        photosRepo.deleteById(id);
    }

    @Override
    public void updateEntity(Photo photo, int id) {
        Optional<Photo> photosOptional = photosRepo.findById(id);
        if(photosOptional.isPresent()){
            Photo photoUpdate = photosOptional.get();
            if(photo.getApartment()!=null){
                photoUpdate.setApartment(photo.getApartment());
            }
            if (photo.getLcd()!=null){
                photoUpdate.setLcd(photo.getLcd());
            }
            if(photo.getFileName()!=null){
                photoUpdate.setFileName(photo.getFileName());
            }
            photosRepo.saveAndFlush(photoUpdate);
        }
    }
}
