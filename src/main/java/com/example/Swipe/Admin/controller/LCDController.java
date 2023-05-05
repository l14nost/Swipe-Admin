package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.service.impl.LCDServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class LCDController {
    private final LCDServiceImpl lcdServiceImpl;
    @GetMapping("/lcd_edit/{id}")
    public String lcdEdit(@PathVariable int id, Model model){
        model.addAttribute("lcd", lcdServiceImpl.findById(id));
        return "admin/lcd_edit";
    }
}
