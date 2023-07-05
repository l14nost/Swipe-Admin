package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.enums.TypeUser;
import com.example.Swipe.Admin.service.impl.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private Logger log = LoggerFactory.getLogger(MainController.class);
    private final UserServiceImpl userService;
    private final NewsServiceImpl newsService;
    private final UserAddInfoServiceImpl userAddInfoService;
    private final LCDServiceImpl lcdService;
    private final ApartmentServiceImpl apartmentService;

    @GetMapping("/statistics")
    public String main(Model model){
        model.addAttribute("client",userService.findAllByType(TypeUser.CLIENT));
        model.addAttribute("contractor",userService.findAllByType(TypeUser.CONTRACTOR));
        model.addAttribute("notary",userService.findAllByType(TypeUser.NOTARY));
        model.addAttribute("houses", apartmentService.count()+lcdService.count());
        model.addAttribute("apartment", apartmentService.count());
        model.addAttribute("apartmentFrame", apartmentService.countNotNull());


        List<Integer> monthlyNews = new ArrayList<>();
        for (int i = 1;i<13;i++){
            monthlyNews.add(newsService.countNews(i));
        }
        model.addAttribute("newsData",monthlyNews);
        List<Integer> monthlySub = new ArrayList<>();
        for (int i = 1;i<13;i++){
            monthlySub.add(userAddInfoService.countSub(i));
        }

        model.addAttribute("subData",monthlySub);
        List<String> month = new ArrayList<>();
        month.add("Январь");
        month.add("Февраль");
        month.add("Март");
        month.add("Апрель");
        month.add("Май");
        month.add("Июнь");
        month.add("Июль");
        month.add("Август");
        month.add("Сентябрь");
        month.add("Октябрь");
        month.add("Ноябрь");
        month.add("Декабрь");
        model.addAttribute("month", month);
        return "admin/admin_main";
    }
}
