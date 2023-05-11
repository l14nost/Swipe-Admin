package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.entity.Apartment;
import com.example.Swipe.Admin.entity.Frame;
import com.example.Swipe.Admin.enums.*;
import com.example.Swipe.Admin.service.impl.*;
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
    private final UserServiceImpl userService;
    private final FrameServiceImpl frameService;
    @GetMapping("/apartment_edit/{id}")
    public String apartmentEdit(@PathVariable int id, Model model){
        model.addAttribute("apartment", apartmentServiceImpl.findById(id));

        model.addAttribute("lcds", lcdService.findAll());
        model.addAttribute("foundingDocument", FoundingDocument.values());
        model.addAttribute("users",userService.findAllByType(TypeUser.CLIENT));
        System.out.println(Arrays.toString(FoundingDocument.values()));
        return "admin/apartment_edit";
    }
    @PostMapping("/apartment_update/{id}")
    public String apartmentUpdate(@PathVariable int id,
                                  @RequestParam int number,
                                  @RequestParam String description,
                                  @RequestParam int price,
                                  @RequestParam int lcd,
                                  @RequestParam int user,
                                  @RequestParam MultipartFile file,
                                  @RequestPart(required = false) List<MultipartFile> galleryPhoto,
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

        Apartment apartment = Apartment.builder().number(number).price(price).lcd(lcdService.findById(lcd)).foundingDocument(foundingDocument).state(state).type(type).balconyType(balconyType).calculation(calculation).countRoom(countRoom).layout(layout).heatingType(heatingType).communicationType(communicationType).commission(commission).totalArea(totalArea).kitchenArea(kitchenArea).user(userService.findById(user)).build();
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
        for(int i = 0; i<preApartment.getPhotoList().size(); i++){
            if (!galleryPhoto.get(i).isEmpty()) {
                File uploadDirGallery = new File(upload);
                if (!uploadDirGallery.exists()) {
                    uploadDirGallery.mkdir();
                }
                String uuid = UUID.randomUUID().toString();
                String fileNameGallery = uuid + "-" + galleryPhoto.get(i).getOriginalFilename();
                String resultNameGallery = upload + "" + fileNameGallery;
                galleryPhoto.get(i).transferTo(new File((resultNameGallery)));
                if (!preApartment.getPhotoList().get(i).getFileName().equals("../admin/dist/img/default.jpg")) {
                    String fileNameDelete = preApartment.getPhotoList().get(i).getFileName().substring(11, preApartment.getPhotoList().get(i).getFileName().length());
                    File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
                    fileDelete.delete();
                }
                preApartment.getPhotoList().get(i).setFileName("../uploads/" + fileNameGallery);
                photosService.updateEntity(preApartment.getPhotoList().get(i),preApartment.getPhotoList().get(i).getIdPhotos() );
            }
        }
        apartmentServiceImpl.updateEntity(apartment,id);

        return "redirect:/houses";

    }

    @GetMapping("/add_apartment")
    public String addPage(Model model){
        model.addAttribute("lcds", lcdService.findAll());
        model.addAttribute("users", userService.findAllByType(TypeUser.CLIENT));
        return "admin/apartment_add";
    }
    @GetMapping("/add_apartment/{idFrame}")
    public String addFramePage(@PathVariable int idFrame,Model model){
        model.addAttribute("idFrame",idFrame);
        return "admin/apartment_frame";
    }

    @PostMapping("/add_apartment")
    public String apartmentAdd(   @RequestParam(required = false) Integer idFrame,
                                  @RequestParam int number,
                                  @RequestParam String description,
                                  @RequestParam int price,
                                  @RequestParam(required = false, defaultValue = "0") int lcd,
                                  @RequestParam(required = false, defaultValue = "0") int user,
                                  @RequestParam MultipartFile file,
//                                  @RequestPart List<MultipartFile> galleryPhoto,
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

        Apartment apartment = Apartment.builder().number(number).price(price).foundingDocument(foundingDocument).state(state).type(type).balconyType(balconyType).calculation(calculation).countRoom(countRoom).layout(layout).heatingType(heatingType).communicationType(communicationType).commission(commission).totalArea(totalArea).kitchenArea(kitchenArea).build();
        if(idFrame!=null){
            Frame frame = frameService.findById(idFrame);
            apartment.setFrame(frame);
            apartment.setLcd(frame.getLcd());
            apartment.setUser(frame.getLcd().getUser());
        }
        else if(user>0){
            apartment.setUser(userService.findById(user));
        }
        if(lcd>0){
            apartment.setLcd(lcdService.findById(lcd));
        }
        if(description.length()>0){
            apartment.setDescription(description);
        }
        if (!file.isEmpty()) {
            File uploadDirGallery = new File(upload);
            if (!uploadDirGallery.exists()) {
                uploadDirGallery.mkdir();
            }
            String uuid = UUID.randomUUID().toString();
            String fileNameGallery = uuid + "-" + file.getOriginalFilename();
            String resultNameGallery = upload + "" + fileNameGallery;
            file.transferTo(new File((resultNameGallery)));
            apartment.setMainPhoto("../uploads/" + fileNameGallery);
        }
        else {
            apartment.setMainPhoto("../admin/dist/img/default.jpg");
        }
//        for(int i = 0; i<preApartment.getPhotosList().size();i++){
//            if (!galleryPhoto.get(i).isEmpty()) {
//                File uploadDirGallery = new File(upload);
//                if (!uploadDirGallery.exists()) {
//                    uploadDirGallery.mkdir();
//                }
//                String uuid = UUID.randomUUID().toString();
//                String fileNameGallery = uuid + "-" + galleryPhoto.get(i).getOriginalFilename();
//                String resultNameGallery = upload + "" + fileNameGallery;
//                galleryPhoto.get(i).transferTo(new File((resultNameGallery)));
//                if (!preApartment.getPhotosList().get(i).getFileName().equals("../admin/dist/img/default.jpg")) {
//                    String fileNameDelete = preApartment.getPhotosList().get(i).getFileName().substring(11, preApartment.getPhotosList().get(i).getFileName().length());
//                    File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
//                    fileDelete.delete();
//                }
//                preApartment.getPhotosList().get(i).setFileName("../uploads/" + fileNameGallery);
//                photosService.updateEntity(preApartment.getPhotosList().get(i),preApartment.getPhotosList().get(i).getIdPhotos() );
//            }
//        }
        apartmentServiceImpl.saveEntity(apartment);
        if(idFrame==null) {
            return "redirect:/houses";
        }
        else {
            return "redirect:/edit_frame/"+idFrame;
        }

    }

}
