package com.example.Swipe.Admin.service;

import com.example.Swipe.Admin.repository.PhotosRepo;
import com.example.Swipe.Admin.repository.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class PhotosService {
    private final PhotosRepo photosRepo;

    public PhotosService(PhotosRepo photosRepo) {
        this.photosRepo = photosRepo;
    }
}
