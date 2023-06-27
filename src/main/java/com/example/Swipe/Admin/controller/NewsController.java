package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.dto.NewsDTO;
import com.example.Swipe.Admin.entity.News;
import com.example.Swipe.Admin.service.impl.LCDServiceImpl;
import com.example.Swipe.Admin.service.impl.NewsServiceImpl;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class NewsController {
    private Logger log = LoggerFactory.getLogger(NewsController.class);
    private final NewsServiceImpl newsServiceImpl;


    @GetMapping("/edit_news/{idNews}")
    public String editNews(@PathVariable int idNews, Model model){
        NewsDTO newsDTO = newsServiceImpl.findByIdDTO(idNews);
        model.addAttribute("news", newsDTO);
        model.addAttribute("idLcd", newsDTO.getIdLcd() );
        return "admin/news_edit";
    }
//    @PostMapping("/update_news")
//    public String updateNews(@RequestParam int idNews, @RequestParam String title, @RequestParam String description, @RequestParam LocalDate date, Model model){
//        News news = News.builder().title(title).date(date).build();
//        if(description.length()>0){
//            news.setDescription(description);
//        }
//        newsServiceImpl.updateEntity(news,idNews);
//        int lcd = newsServiceImpl.findById(idNews).getLcd().getIdLcd();
//        log.info("News id:"+idNews+", for lcd:"+lcd+", was update");
//        return "redirect:/lcd_edit/"+lcd;
//    }
    @PostMapping("/edit_news/{idNews}")
    public String updateNews(@PathVariable int idNews, @ModelAttribute(name = "news") @Valid NewsDTO newsDTO,BindingResult result, Model model){
        if (result.hasErrors()){
            System.out.println(result.getAllErrors());
            model.addAttribute("news", newsDTO);
            model.addAttribute("idLcd", newsDTO.getIdLcd());
            return "admin/news_edit";
        }
        newsServiceImpl.updateDTO(newsDTO,idNews);
        int lcd = newsServiceImpl.findById(idNews).getLcd().getIdLcd();
        log.info("News id:"+idNews+", for lcd:"+lcd+", was update");
        return "redirect:/lcd_edit/"+lcd;
    }

    @GetMapping("/add_news/{idLcd}")
    public String addNews(@PathVariable int idLcd, Model model){
        model.addAttribute("idLcd", idLcd);
        model.addAttribute("news", NewsDTO.builder().build());
        return "admin/news_add";
    }
//    @PostMapping("/add_news")
//    public String saveNews(@RequestParam int idLcd, @RequestParam String title, @RequestParam String description, @RequestParam LocalDate date, Model model){
//        News news = News.builder().title(title).date(date).lcd(lcdService.findById(idLcd)).description(description).build();
//        newsServiceImpl.saveEntity(news);
//        log.info("News"+", for lcd:"+idLcd+", was add");
//        return "redirect:/lcd_edit/"+idLcd;
//    }
    @PostMapping("/add_news/{idLcd}")
    public String saveNews(@PathVariable int idLcd,@ModelAttribute(name = "news") @Valid NewsDTO newsDTO, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
            model.addAttribute("idLcd",newsDTO.getIdLcd());
            model.addAttribute("news",newsDTO);
            return "admin/news_add";
        }
        newsServiceImpl.saveDTO(newsDTO);
        log.info("News"+", for lcd:"+idLcd+", was add");
        return "redirect:/lcd_edit/"+idLcd;
    }

    @PostMapping("/delete_news")
    public String deleteNews(@RequestParam int idNews, @RequestParam int idLcd, Model model){
        newsServiceImpl.deleteById(idNews);
        log.info("News id:"+idNews+", for lcd:"+idLcd+", was delete");
        return "redirect:/lcd_edit/"+idLcd;
    }

}
