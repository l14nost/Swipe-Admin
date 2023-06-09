package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.entity.Apartment;
import com.example.Swipe.Admin.entity.LCD;
import com.example.Swipe.Admin.entity.Photo;
import com.example.Swipe.Admin.service.impl.ApartmentServiceImpl;
import com.example.Swipe.Admin.service.impl.LCDServiceImpl;
import com.example.Swipe.Admin.service.impl.PhotosServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@Controller
@RequiredArgsConstructor
public class PhotosController {
    private Logger log = LoggerFactory.getLogger(PhotosController.class);
    @Value("${upload.path}")
    private String upload;
    private final PhotosServiceImpl photosServiceImpl;
    @GetMapping("/add_photo/{idApartment}")
    public String addPhoto(@PathVariable int idApartment, Model model){
        Photo photo = Photo.builder().fileName("../admin/dist/img/default.jpg").apartment(Apartment.builder().idApartment(idApartment).build()).build();
        photosServiceImpl.saveEntity(photo);
        log.info("Photo, for apartment:"+idApartment+", was add");
        return "redirect:/apartment_edit/"+idApartment;
    }
    @GetMapping("/add_photo_lcd/{idLcd}")
    public String addPhotoLcd(@PathVariable int idLcd, Model model){
        Photo photo = Photo.builder().fileName("../admin/dist/img/default.jpg").lcd(LCD.builder().idLcd(idLcd).build()).build();
        photosServiceImpl.saveEntity(photo);
        log.info("Photo, for lcd:"+idLcd+", was add");

        return "redirect:/lcd_edit/"+idLcd;
    }
    @PostMapping("/delete_photo_apartment")
    public String deletePhoto(@RequestParam(name = "idPhoto") int idPhoto,@RequestParam(name = "idApartment") int idApartment){

        photosServiceImpl.deleteById(idPhoto);
        log.info("Photo, for apartment:"+idApartment+", was delete");
        return "redirect:/apartment_edit/"+idApartment;
    }

    @PostMapping("/delete_photo_lcd")
    public String deletePhotoLcd(@RequestParam(name = "idPhoto") int idPhoto,@RequestParam(name = "idLcd") int idLcd){
//        Photo photo = photosServiceImpl.findById(idPhoto);
//        System.out.println(photo.getFileName());
//        if (!photo.getFileName().equals("../admin/dist/img/default.jpg")) {
//            String fileNameDelete = photo.getFileName().substring(11, photo.getFileName().length());
//            File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
//            fileDelete.delete();
//        }
        photosServiceImpl.deleteById(idPhoto);
        log.info("Photo, for lcd:"+idLcd+", was delete");

        return "redirect:/lcd_edit/"+idLcd;
    }

}
