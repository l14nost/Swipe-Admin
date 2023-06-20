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

    @GetMapping("/main")
    public String main(Model model){
        model.addAttribute("client",userService.findAllByType(TypeUser.CLIENT));
        model.addAttribute("contractor",userService.findAllByType(TypeUser.CONTRACTOR));
        model.addAttribute("notary",userService.findAllByType(TypeUser.NOTARY));
        List<Integer> monthlyNews = new ArrayList<>();
        for (int i = 1;i<13;i++){
            monthlyNews.add(newsService.countNews(i));
        }
        model.addAttribute("newsData",monthlyNews);
        List<String> month = new ArrayList<>();
        month.add("January");
        month.add("February");
        month.add("March");
        month.add("April");
        month.add("May");
        month.add("June");
        month.add("July");
        month.add("August");
        month.add("September");
        month.add("October");
        month.add("November");
        month.add("December");
        model.addAttribute("month", month);
        return "admin/admin_main";
    }
}
