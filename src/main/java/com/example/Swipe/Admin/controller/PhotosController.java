package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.service.impl.PhotosServiceImpl;
import org.springframework.stereotype.Controller;

@Controller
public class PhotosController {
    private final PhotosServiceImpl photosServiceImpl;

    public PhotosController(PhotosServiceImpl photosServiceImpl) {
        this.photosServiceImpl = photosServiceImpl;
    }
}
