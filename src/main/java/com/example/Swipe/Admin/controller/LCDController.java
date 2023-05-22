package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.entity.Apartment;
import com.example.Swipe.Admin.entity.LCD;
import com.example.Swipe.Admin.enums.*;
import com.example.Swipe.Admin.service.impl.DocumentsServiceImpl;
import com.example.Swipe.Admin.service.impl.LCDServiceImpl;
import com.example.Swipe.Admin.service.impl.PhotosServiceImpl;
import com.example.Swipe.Admin.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

@Controller
@RequiredArgsConstructor
public class LCDController {
    @Value("${upload.path}")
    private String upload;
    private final LCDServiceImpl lcdServiceImpl;
    private final PhotosServiceImpl photosService;
    private final DocumentsServiceImpl documentsService;
    private final UserServiceImpl userService;
    @GetMapping("/lcd_edit/{id}")
    public String lcdEdit(@PathVariable int id, Model model){
        List<String> advantages = List.of(lcdServiceImpl.findById(id).getAdvantages().split(","));
        model.addAttribute("advantages", advantages);
        model.addAttribute("lcd", lcdServiceImpl.findById(id));
        model.addAttribute("contractors", userService.findAllByType(TypeUser.CONTRACTOR));
        return "admin/lcd_edit";
    }
    @PostMapping("/lcd_update/{id}")
    public String lcdUpdate(@PathVariable int id,
                                  @RequestParam String name,
                                  @RequestParam String description,
                                  @RequestParam int contractor,
                                  @RequestParam String advantages,
                                  @RequestParam String formalization,
                                  @RequestParam String typePayment,
                                  @RequestParam String appointment,
                                  @RequestParam String sumContract,
                                  @RequestParam MultipartFile file,
                                  @RequestPart(required = false) List<MultipartFile> gallery,
                                  @RequestPart(required = false) List<MultipartFile> documents,
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
        System.out.println(advantages);
        LCD lcd = LCD.builder().name(name).advantages(advantages).status(status).type(type).lcdClass(lcdClass).technology(technology).territory(territory).gas(gas).sewerage(sewerage).waterSupply(waterSupply).communal(communal).heating(heating).height(height).distanceSea(distanceSea).user(userService.findById(contractor)).sumContract(sumContract).appointment(appointment).formalization(formalization).typePayment(typePayment).build();
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
        for(int i = 0; i<preLcd.getPhotoList().size(); i++){
            if (!gallery.get(i).isEmpty()) {
                File uploadDirGallery = new File(upload);
                if (!uploadDirGallery.exists()) {
                    uploadDirGallery.mkdir();
                }
                String uuid = UUID.randomUUID().toString();
                String fileNameGallery = uuid + "-" + gallery.get(i).getOriginalFilename();
                String resultNameGallery = upload + "" + fileNameGallery;
                gallery.get(i).transferTo(new File((resultNameGallery)));
                if (!preLcd.getPhotoList().get(i).getFileName().equals("../admin/dist/img/default.jpg")) {
                    String fileNameDelete = preLcd.getPhotoList().get(i).getFileName().substring(11, preLcd.getPhotoList().get(i).getFileName().length());
                    File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
                    fileDelete.delete();
                }
                preLcd.getPhotoList().get(i).setFileName("../uploads/" + fileNameGallery);
                photosService.updateEntity(preLcd.getPhotoList().get(i),preLcd.getPhotoList().get(i).getIdPhotos() );
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
    @GetMapping("/add_lcd")
    public String addPage(Model model){
        model.addAttribute("contractors",userService.findAllByType(TypeUser.CONTRACTOR));
        return "admin/lcd_add";
    }


    @PostMapping("/lcd_add")
    public String lcdAdd(
                            @RequestParam String name,
                            @RequestParam String description,
                            @RequestParam String advantages,
                            @RequestParam String formalization,
                            @RequestParam String typePayment,
                            @RequestParam String appointment,
                            @RequestParam String sumContract,
                            @RequestParam MultipartFile file,
//                            @RequestPart(required = false) List<MultipartFile> gallery,
//                            @RequestPart List<MultipartFile> documents,
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

        LCD lcd = LCD.builder().name(name).advantages(advantages).status(status).type(type).lcdClass(lcdClass).technology(technology).territory(territory).gas(gas).sewerage(sewerage).waterSupply(waterSupply).communal(communal).heating(heating).height(height).distanceSea(distanceSea).formalization(formalization).typePayment(typePayment).appointment(appointment).sumContract(sumContract).build();
        if(description.length()>0){
            lcd.setDescription(description);
        }

//        LCD preLcd = lcdServiceImpl.findById(id);
//        lcd.setDocuments(preLcd.getDocuments());
        if (!file.isEmpty()) {
            File uploadDirGallery = new File(upload);
            if (!uploadDirGallery.exists()) {
                uploadDirGallery.mkdir();
            }
            String uuid = UUID.randomUUID().toString();
            String fileNameGallery = uuid + "-" + file.getOriginalFilename();
            String resultNameGallery = upload + "" + fileNameGallery;
            file.transferTo(new File((resultNameGallery)));
//            if (!preLcd.getMainPhoto().equals("../admin/dist/img/default.jpg")) {
//                String fileNameDelete = preLcd.getMainPhoto().substring(11, preLcd.getMainPhoto().length());
//                File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
//                fileDelete.delete();
//            }
            lcd.setMainPhoto("../uploads/" + fileNameGallery);
        }
        else {
            lcd.setMainPhoto("../admin/dist/img/default.jpg");
        }
//        for(int i = 0; i<preLcd.getPhotosList().size();i++){
//            if (!gallery.get(i).isEmpty()) {
//                File uploadDirGallery = new File(upload);
//                if (!uploadDirGallery.exists()) {
//                    uploadDirGallery.mkdir();
//                }
//                String uuid = UUID.randomUUID().toString();
//                String fileNameGallery = uuid + "-" + gallery.get(i).getOriginalFilename();
//                String resultNameGallery = upload + "" + fileNameGallery;
//                gallery.get(i).transferTo(new File((resultNameGallery)));
//                if (!preLcd.getPhotosList().get(i).getFileName().equals("../admin/dist/img/default.jpg")) {
//                    String fileNameDelete = preLcd.getPhotosList().get(i).getFileName().substring(11, preLcd.getPhotosList().get(i).getFileName().length());
//                    File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
//                    fileDelete.delete();
//                }
//                preLcd.getPhotosList().get(i).setFileName("../uploads/" + fileNameGallery);
//                photosService.updateEntity(preLcd.getPhotosList().get(i),preLcd.getPhotosList().get(i).getIdPhotos() );
//            }
//        }
//        for(int i = 0; i<preLcd.getDocuments().size();i++){
//            if (!documents.get(i).isEmpty()) {
//                File uploadDirGallery = new File(upload);
//                if (!uploadDirGallery.exists()) {
//                    uploadDirGallery.mkdir();
//                }
//                String uuid = UUID.randomUUID().toString();
//                String fileNameGallery = uuid + "-" + documents.get(i).getOriginalFilename();
//                lcd.getDocuments().get(i).setName(documents.get(i).getOriginalFilename());
//                String resultNameGallery = upload + "" + fileNameGallery;
//                documents.get(i).transferTo(new File((resultNameGallery)));
//                if (!preLcd.getDocuments().get(i).getFileName().equals("../admin/dist/img/default.jpg")) {
//                    String fileNameDelete = preLcd.getDocuments().get(i).getFileName().substring(11, preLcd.getDocuments().get(i).getFileName().length());
//                    File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
//                    fileDelete.delete();
//                }
//                lcd.getDocuments().get(i).setFileName("../uploads/" + fileNameGallery);
//                documentsService.updateEntity(lcd.getDocuments().get(i),lcd.getDocuments().get(i).getIdDocuments() );
//            }
//        }
        lcdServiceImpl.saveEntity(lcd);

        return "redirect:/houses";

    }
    @PostMapping("/delete_lcd")
    public String deleteLcd(int idLcd, Model model){
        LCD lcd = lcdServiceImpl.findById(idLcd);
        if (!lcd.getMainPhoto().equals("../admin/dist/img/default.jpg")) {
            String fileNameDelete = lcd.getMainPhoto().substring(11, lcd.getMainPhoto().length());
            File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
            fileDelete.delete();
        }
        for(int i = 0; i< lcd.getPhotoList().size();i++){
            if (!lcd.getPhotoList().get(i).getFileName().equals("../admin/dist/img/default.jpg")) {
                String fileNameDelete = lcd.getPhotoList().get(i).getFileName().substring(11, lcd.getPhotoList().get(i).getFileName().length());
                File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
                fileDelete.delete();
            }
        }
        for(int i = 0; i< lcd.getDocuments().size();i++){
            if (!lcd.getDocuments().get(i).getFileName().equals("../admin/dist/img/default.jpg")) {
                String fileNameDelete = lcd.getDocuments().get(i).getFileName().substring(11, lcd.getDocuments().get(i).getFileName().length());
                File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
                fileDelete.delete();
            }
        }

        for (int i = 0; i<lcd.getFrames().size();i++){
            for(int j = 0;j<lcd.getFrames().get(i).getApartmentList().size();j++){
                if (!lcd.getFrames().get(i).getApartmentList().get(j).getMainPhoto().equals("../admin/dist/img/default.jpg")) {
                    String fileNameDelete = lcd.getFrames().get(i).getApartmentList().get(j).getMainPhoto().substring(11, lcd.getFrames().get(i).getApartmentList().get(j).getMainPhoto().length());
                    File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
                    fileDelete.delete();
                }
                for (int k = 0; k < lcd.getFrames().get(i).getApartmentList().get(j).getPhotoList().size(); k++) {
                    if (!lcd.getFrames().get(i).getApartmentList().get(j).getPhotoList().get(k).getFileName().equals("../admin/dist/img/default.jpg")) {
                        String fileNameDelete = lcd.getFrames().get(i).getApartmentList().get(j).getPhotoList().get(k).getFileName().substring(11, lcd.getFrames().get(i).getApartmentList().get(j).getPhotoList().get(k).getFileName().length());
                        File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
                        fileDelete.delete();
                    }

                }
            }
        }
        lcdServiceImpl.deleteById(idLcd);

        return "redirect:/houses";

    }
    public static final boolean checkTypes(Stream stream, String typeVal){
        return stream.anyMatch(v->v.equals(typeVal));
    }

}
