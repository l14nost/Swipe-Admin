package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.entity.Frame;
import com.example.Swipe.Admin.entity.Photo;
import com.example.Swipe.Admin.service.impl.FrameServiceImpl;
import com.example.Swipe.Admin.service.impl.LCDServiceImpl;
import com.example.Swipe.Admin.service.impl.PhotosServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;

@Controller
@RequiredArgsConstructor
public class FrameController {
    @Value("${upload.path}")
    private String upload;
    private final FrameServiceImpl frameService;
    private final LCDServiceImpl lcdService;
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
    public String editFrame(@PathVariable int idFrame, Model model){
        model.addAttribute("frame", frameService.findById(idFrame));
        return "admin/frame_edit";
    }

}
