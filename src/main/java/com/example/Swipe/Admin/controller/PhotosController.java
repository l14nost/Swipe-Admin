package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.entity.Photos;
import com.example.Swipe.Admin.service.impl.ApartmentServiceImpl;
import com.example.Swipe.Admin.service.impl.PhotosServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class PhotosController {
    private final PhotosServiceImpl photosServiceImpl;
    private final ApartmentServiceImpl apartmentService;
    @GetMapping("/add_photo/{idApartment}")
    public String addPhoto(@PathVariable int idApartment, Model model){
        Photos photos = Photos.builder().fileName("../admin/dist/img/default.jpg").apartment(apartmentService.findById(idApartment)).build();
        photosServiceImpl.saveEntity(photos);
        return "redirect:/apartment_edit/"+idApartment;
    }

}
