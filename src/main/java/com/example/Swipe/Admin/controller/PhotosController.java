package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.service.PhotosService;
import com.example.Swipe.Admin.service.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class PhotosController {
    private final PhotosService photosService;

    public PhotosController(PhotosService photosService) {
        this.photosService = photosService;
    }
}
