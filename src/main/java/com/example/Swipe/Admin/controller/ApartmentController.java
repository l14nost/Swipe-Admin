package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.entity.Apartment;
import com.example.Swipe.Admin.entity.LCD;
import com.example.Swipe.Admin.enums.*;
import com.example.Swipe.Admin.service.impl.ApartmentServiceImpl;
import com.example.Swipe.Admin.service.impl.LCDServiceImpl;
import com.example.Swipe.Admin.service.impl.PhotosServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ApartmentController {
    @Value("${upload.path}")
    private String upload;
    private final ApartmentServiceImpl apartmentServiceImpl;
    private final LCDServiceImpl lcdService;
    private final PhotosServiceImpl photosService;
    @GetMapping("/apartment_edit/{id}")
    public String apartmentEdit(@PathVariable int id, Model model){
        model.addAttribute("apartment", apartmentServiceImpl.findById(id));

        model.addAttribute("lcds", lcdService.findAll());
        model.addAttribute("foundingDocument", FoundingDocument.values());
        System.out.println(Arrays.toString(FoundingDocument.values()));
        return "admin/apartment_edit";
    }
    @PostMapping("/apartment_update/{id}")
    public String apartmentUpdate(@PathVariable int id,
                                  @RequestParam int number,
                                  @RequestParam String description,
                                  @RequestParam int price,
                                  @RequestParam int lcd,
                                  @RequestParam MultipartFile file,
                                  @RequestPart List<MultipartFile> galleryPhoto,
                                  @RequestParam FoundingDocument foundingDocument,
                                  @RequestParam State state,
                                  @RequestParam TypeApartment type,
                                  @RequestParam BalconyType balconyType,
                                  @RequestParam Calculation calculation,
                                  @RequestParam CountRoom countRoom,
                                  @RequestParam LayoutType layout,
                                  @RequestParam HeatingType heatingType,
                                  @RequestParam CommunicationType communicationType,
                                  @RequestParam Commission commission,
                                  @RequestParam int totalArea,
                                  @RequestParam int kitchenArea, Model model) throws IOException {

        Apartment apartment = Apartment.builder().number(number).price(price).lcd(lcdService.findById(lcd)).foundingDocument(foundingDocument).state(state).type(type).balconyType(balconyType).calculation(calculation).countRoom(countRoom).layout(layout).heatingType(heatingType).communicationType(communicationType).commission(commission).totalArea(totalArea).kitchenArea(kitchenArea).build();
        if(description.length()>0){
            apartment.setDescription(description);
        }
        Apartment preApartment = apartmentServiceImpl.findById(id);
        if (!file.isEmpty()) {
            File uploadDirGallery = new File(upload);
            if (!uploadDirGallery.exists()) {
                uploadDirGallery.mkdir();
            }
            String uuid = UUID.randomUUID().toString();
            String fileNameGallery = uuid + "-" + file.getOriginalFilename();
            String resultNameGallery = upload + "" + fileNameGallery;
            file.transferTo(new File((resultNameGallery)));
            if (!preApartment.getMainPhoto().equals("../admin/dist/img/default.jpg")) {
                String fileNameDelete = preApartment.getMainPhoto().substring(11, preApartment.getMainPhoto().length());
                File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
                fileDelete.delete();
            }
            apartment.setMainPhoto("../uploads/" + fileNameGallery);
        }
        for(int i = 0; i<preApartment.getPhotosList().size();i++){
            if (!galleryPhoto.get(i).isEmpty()) {
                File uploadDirGallery = new File(upload);
                if (!uploadDirGallery.exists()) {
                    uploadDirGallery.mkdir();
                }
                String uuid = UUID.randomUUID().toString();
                String fileNameGallery = uuid + "-" + galleryPhoto.get(i).getOriginalFilename();
                String resultNameGallery = upload + "" + fileNameGallery;
                galleryPhoto.get(i).transferTo(new File((resultNameGallery)));
                if (!preApartment.getPhotosList().get(i).getFileName().equals("../admin/dist/img/default.jpg")) {
                    String fileNameDelete = preApartment.getPhotosList().get(i).getFileName().substring(11, preApartment.getPhotosList().get(i).getFileName().length());
                    File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
                    fileDelete.delete();
                }
                preApartment.getPhotosList().get(i).setFileName("../uploads/" + fileNameGallery);
                photosService.updateEntity(preApartment.getPhotosList().get(i),preApartment.getPhotosList().get(i).getIdPhotos() );
            }
        }
        apartmentServiceImpl.updateEntity(apartment,id);

        return "redirect:/houses";

    }

}
