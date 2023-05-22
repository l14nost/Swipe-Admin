package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.service.impl.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MainHousesPageController {
    private final LCDServiceImpl lcdService;
    private final ApartmentServiceImpl apartmentService;

    @GetMapping("/houses")
    public String housesMain(@RequestParam(name = "lcdPage",required = false,defaultValue = "0") int lcdPage,
                             @RequestParam(name = "lcdSize",required = false,defaultValue = "3") int lcdSize,
                             @RequestParam(name = "apartmentPage",required = false,defaultValue = "0") int apartmentPage,
                             @RequestParam(name = "apartmentSize",required = false,defaultValue = "3") int apartmentSize,
                             @RequestParam(name = "searchLcd",required = false,defaultValue = "null") String keyWordLcd,
                             @RequestParam(name = "searchApartment",required = false,defaultValue = "0") int keyWordApartment,
                             Model model) {
        Pageable lcdPageable = PageRequest.of(lcdPage,lcdSize);
        Pageable apartmentPageable = PageRequest.of(apartmentPage,apartmentSize);
        model.addAttribute("lcds",lcdService.findAllPagination(lcdPageable,keyWordLcd));
        model.addAttribute("apartments", apartmentService.findAllByFramePagination(apartmentPageable,keyWordApartment));

        model.addAttribute("searchLcd",keyWordLcd);
        model.addAttribute("searchApartment",keyWordApartment);
        return "admin/houses_main";
    }
}
