package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.entity.Frame;
import com.example.Swipe.Admin.entity.Photo;
import com.example.Swipe.Admin.service.impl.ApartmentServiceImpl;
import com.example.Swipe.Admin.service.impl.FrameServiceImpl;
import com.example.Swipe.Admin.service.impl.LCDServiceImpl;
import com.example.Swipe.Admin.service.impl.PhotosServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@Controller
@RequiredArgsConstructor
public class FrameController {
    private Logger log = LoggerFactory.getLogger(FrameController.class);
    @Value("${upload.path}")
    private String upload;
    private final FrameServiceImpl frameService;
    private final LCDServiceImpl lcdService;
    private final ApartmentServiceImpl apartmentService;
    @GetMapping("/add_frame/{idLcd}")
    public String addFrame(@PathVariable int idLcd, Model model){
        Frame frame = Frame.builder().lcd(lcdService.findById(idLcd)).build();
        if(lcdService.findById(idLcd).getFrames().size()>0){
            frame.setNum(lcdService.findById(idLcd).getFrames().size()+1);
        }
        else {
            frame.setNum(1);
        }
        frameService.saveEntity(frame);
        return "redirect:/lcd_edit/"+idLcd;
    }
    @GetMapping("/edit_frame/{idFrame}")
    public String editFrame(
            @PathVariable int idFrame,
            @RequestParam(name = "apartmentPage", required = false, defaultValue = "0") int apartmentPage,
            @RequestParam(name = "apartmentSize", required = false, defaultValue = "3") int apartmentSize,
            @RequestParam(name = "searchApartment", required = false, defaultValue = "0")int keyWord,
            @RequestParam(name = "sortedApartment", required = false, defaultValue = "idApartment")String field,
            Model model){
        Pageable pageable = PageRequest.of(apartmentPage,apartmentSize);
        model.addAttribute("searchApartment", keyWord);
        model.addAttribute("frame", frameService.findById(idFrame));
        model.addAttribute("apartments", apartmentService.findAllForFramePagination(frameService.findById(idFrame),pageable,keyWord,field ));

        model.addAttribute("sizeApartment", apartmentService.count(frameService.findById(idFrame)));
        return "admin/frame_edit";
    }

    @PostMapping("/frame_update")
    public String updateFrame(@RequestParam int idFrame, @RequestParam int number){
        Frame frame = Frame.builder().num(number).build();
        frameService.updateEntity(frame,idFrame);
        log.info("Frame id:"+idFrame+", was update");
        System.out.println("+++");
        return "redirect:/lcd_edit/"+frameService.findById(idFrame).getLcd().getIdLcd();
    }

    @PostMapping("/delete_frame")
    public String deleteFrameById(@RequestParam(name = "idFrame") int idFrame,@RequestParam(name = "idLcd") int idLcd, Model model){
        Frame frame = frameService.findById(idFrame);
        if (frame.getApartmentList().size()>0) {
            for (int i = 0; i < frame.getApartmentList().size(); i++) {
                if (!frame.getApartmentList().get(i).getMainPhoto().equals("../admin/dist/img/default.jpg")) {
                    String fileNameDelete = frame.getApartmentList().get(i).getMainPhoto().substring(11, frame.getApartmentList().get(i).getMainPhoto().length());
                    File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
                    fileDelete.delete();
                }
                if (frame.getApartmentList().get(i).getPhotoList().size()>0) {
                    for (int j = 0; j < frame.getApartmentList().get(i).getPhotoList().size(); j++) {
                        if (!frame.getApartmentList().get(i).getPhotoList().get(j).getFileName().equals("../admin/dist/img/default.jpg")) {
                            String fileNameDelete = frame.getApartmentList().get(i).getPhotoList().get(j).getFileName().substring(11, frame.getApartmentList().get(i).getPhotoList().get(j).getFileName().length());
                            File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
                            fileDelete.delete();
                        }

                    }
                }
            }
        }
        log.info("Frame id:"+idFrame+", was delete");
       frameService.deleteById(idFrame);

        return "redirect:/lcd_edit/"+idLcd;
    }




}
