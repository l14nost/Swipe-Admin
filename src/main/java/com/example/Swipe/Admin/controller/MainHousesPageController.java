package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.service.impl.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MainHousesPageController {
    private Logger log = LoggerFactory.getLogger(MainHousesPageController.class);
    private final LCDServiceImpl lcdService;
    private final ApartmentServiceImpl apartmentService;

    @GetMapping("/houses")
    public String housesMain(@RequestParam(name = "lcdPage",required = false,defaultValue = "0") int lcdPage,
                             @RequestParam(name = "lcdSize",required = false,defaultValue = "3") int lcdSize,
                             @RequestParam(name = "apartmentPage",required = false,defaultValue = "0") int apartmentPage,
                             @RequestParam(name = "apartmentSize",required = false,defaultValue = "3") int apartmentSize,
                             @RequestParam(name = "searchLcd",required = false,defaultValue = "null") String keyWordLcd,
                             @RequestParam(name = "searchApartment",required = false,defaultValue = "0") int keyWordApartment,
                             @RequestParam(name = "sortedLcd",required = false,defaultValue = "idLcd") String fieldLcd,
                             @RequestParam(name = "sortedApartment",required = false,defaultValue = "idApartment") String fieldApartment,
                             Model model) {
        Pageable lcdPageable = PageRequest.of(lcdPage,lcdSize);
        Pageable apartmentPageable = PageRequest.of(apartmentPage,apartmentSize);
        model.addAttribute("lcds",lcdService.findAllPagination(lcdPageable,keyWordLcd,fieldLcd));
        model.addAttribute("apartments", apartmentService.findAllByFramePagination(apartmentPageable,keyWordApartment,fieldApartment));

        model.addAttribute("searchLcd",keyWordLcd);
        model.addAttribute("searchApartment",keyWordApartment);

        model.addAttribute("sizeLcd", lcdService.count());
        model.addAttribute("sizeApartment", apartmentService.count());
        log.info("Current page lcd table:"+lcdPage+", size:"+lcdSize);
        log.info("Current page aparment table:"+apartmentPage+", size:"+apartmentSize);
        return "admin/houses_main";
    }
}
