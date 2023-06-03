package com.example.Swipe.Admin.service.impl;

import com.example.Swipe.Admin.entity.Photo;
import com.example.Swipe.Admin.repository.PhotosRepo;
import com.example.Swipe.Admin.service.PhotosService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Setter
public class PhotosServiceImpl implements PhotosService {
    @Value("${upload.path}")
    private String upload;
    private final PhotosRepo photosRepo;


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
            return null;
        }
    }

    @Override
    public void saveEntity(Photo photo) {
        photosRepo.save(photo);
    }

    @Override
    public void deleteById(int id) {
        Optional<Photo> photo = photosRepo.findById(id);
        if (photo.isPresent()) {
            if (!photo.get().getFileName().equals("../admin/dist/img/default.jpg")) {
                String fileNameDelete = photo.get().getFileName().substring(11, photo.get().getFileName().length());
                File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
                fileDelete.delete();
            }
        }
        photosRepo.deleteById(id);
    }

    @Override
    public void updateEntity(Photo photo, int id) {
        Optional<Photo> photosOptional = photosRepo.findById(id);
        if(photosOptional.isPresent()){
            Photo photoUpdate = photosOptional.get();
            if(photo.getFileName()!=null){
                photoUpdate.setFileName(photo.getFileName());
            }
            photosRepo.saveAndFlush(photoUpdate);
        }
    }
}
