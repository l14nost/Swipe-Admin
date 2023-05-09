package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.entity.Apartment;
import com.example.Swipe.Admin.entity.Documents;
import com.example.Swipe.Admin.entity.LCD;
import com.example.Swipe.Admin.enums.*;
import com.example.Swipe.Admin.service.impl.DocumentsServiceImpl;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class LCDController {
    @Value("${upload.path}")
    private String upload;
    private final LCDServiceImpl lcdServiceImpl;
    private final PhotosServiceImpl photosService;
    private final DocumentsServiceImpl documentsService;
    @GetMapping("/lcd_edit/{id}")
    public String lcdEdit(@PathVariable int id, Model model){
        model.addAttribute("lcd", lcdServiceImpl.findById(id));
        return "admin/lcd_edit";
    }
    @PostMapping("/lcd_update/{id}")
    public String lcdUpdate(@PathVariable int id,
                                  @RequestParam String name,
                                  @RequestParam String description,
                                  @RequestParam String advantages,
                                  @RequestParam MultipartFile file,
                                  @RequestPart(required = false) List<MultipartFile> gallery,
                                  @RequestPart List<MultipartFile> documents,
                                  @RequestParam StatusLCDType status,
                                  @RequestParam LCDType type,
                                  @RequestParam ClassType lcdClass,
                                  @RequestParam TechnologyType technology,
                                  @RequestParam TerritoryType territory,
                                  @RequestParam GasType gas,
                                  @RequestParam HeatingType sewerage,
                                  @RequestParam HeatingType waterSupply,
                                  @RequestParam CommunalType communal,
                                  @RequestParam HeatingType heating,
                                  @RequestParam int height,
                                  @RequestParam int distanceSea,
                                  Model model) throws IOException {

        LCD lcd = LCD.builder().name(name).advantages(advantages).status(status).type(type).lcdClass(lcdClass).technology(technology).territory(territory).gas(gas).sewerage(sewerage).waterSupply(waterSupply).communal(communal).heating(heating).height(height).distanceSea(distanceSea).build();
        if(description.length()>0){
            lcd.setDescription(description);
        }

        LCD preLcd = lcdServiceImpl.findById(id);
        lcd.setDocuments(preLcd.getDocuments());
        if (!file.isEmpty()) {
            File uploadDirGallery = new File(upload);
            if (!uploadDirGallery.exists()) {
                uploadDirGallery.mkdir();
            }
            String uuid = UUID.randomUUID().toString();
            String fileNameGallery = uuid + "-" + file.getOriginalFilename();
            String resultNameGallery = upload + "" + fileNameGallery;
            file.transferTo(new File((resultNameGallery)));
            if (!preLcd.getMainPhoto().equals("../admin/dist/img/default.jpg")) {
                String fileNameDelete = preLcd.getMainPhoto().substring(11, preLcd.getMainPhoto().length());
                File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
                fileDelete.delete();
            }
            lcd.setMainPhoto("../uploads/" + fileNameGallery);
        }
        for(int i = 0; i<preLcd.getPhotosList().size();i++){
            if (!gallery.get(i).isEmpty()) {
                File uploadDirGallery = new File(upload);
                if (!uploadDirGallery.exists()) {
                    uploadDirGallery.mkdir();
                }
                String uuid = UUID.randomUUID().toString();
                String fileNameGallery = uuid + "-" + gallery.get(i).getOriginalFilename();
                String resultNameGallery = upload + "" + fileNameGallery;
                gallery.get(i).transferTo(new File((resultNameGallery)));
                if (!preLcd.getPhotosList().get(i).getFileName().equals("../admin/dist/img/default.jpg")) {
                    String fileNameDelete = preLcd.getPhotosList().get(i).getFileName().substring(11, preLcd.getPhotosList().get(i).getFileName().length());
                    File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
                    fileDelete.delete();
                }
                preLcd.getPhotosList().get(i).setFileName("../uploads/" + fileNameGallery);
                photosService.updateEntity(preLcd.getPhotosList().get(i),preLcd.getPhotosList().get(i).getIdPhotos() );
            }
        }
        for(int i = 0; i<preLcd.getDocuments().size();i++){
            if (!documents.get(i).isEmpty()) {
                File uploadDirGallery = new File(upload);
                if (!uploadDirGallery.exists()) {
                    uploadDirGallery.mkdir();
                }
                String uuid = UUID.randomUUID().toString();
                String fileNameGallery = uuid + "-" + documents.get(i).getOriginalFilename();
                lcd.getDocuments().get(i).setName(documents.get(i).getOriginalFilename());
                String resultNameGallery = upload + "" + fileNameGallery;
                documents.get(i).transferTo(new File((resultNameGallery)));
                if (!preLcd.getDocuments().get(i).getFileName().equals("../admin/dist/img/default.jpg")) {
                    String fileNameDelete = preLcd.getDocuments().get(i).getFileName().substring(11, preLcd.getDocuments().get(i).getFileName().length());
                    File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
                    fileDelete.delete();
                }
                lcd.getDocuments().get(i).setFileName("../uploads/" + fileNameGallery);
                documentsService.updateEntity(lcd.getDocuments().get(i),lcd.getDocuments().get(i).getIdDocuments() );
            }
        }
        lcdServiceImpl.updateEntity(lcd,id);

        return "redirect:/houses";

    }
}
