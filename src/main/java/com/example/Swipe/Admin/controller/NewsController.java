package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.entity.News;
import com.example.Swipe.Admin.service.impl.LCDServiceImpl;
import com.example.Swipe.Admin.service.impl.NewsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class NewsController {
    private final NewsServiceImpl newsServiceImpl;
    private final LCDServiceImpl lcdService;


    @GetMapping("/edit_news/{idNews}")
    public String editNews(@PathVariable int idNews, Model model){
        model.addAttribute("news", newsServiceImpl.findById(idNews));
        return "admin/news_edit";
    }
    @PostMapping("/update_news")
    public String updateNews(@RequestParam int idNews, @RequestParam String title, @RequestParam String description, @RequestParam LocalDate date, Model model){
        News news = News.builder().title(title).date(date).build();
        if(description.length()>0){
            news.setDescription(description);
        }
        newsServiceImpl.updateEntity(news,idNews);
        return "redirect:/lcd_edit/"+newsServiceImpl.findById(idNews).getLcd().getIdLcd();
    }

    @GetMapping("/add_news/{idLcd}")
    public String addNews(@PathVariable int idLcd, Model model){
        model.addAttribute("idLcd", idLcd);
        return "admin/news_add";
    }
    @PostMapping("/add_news")
    public String saveNews(@RequestParam int idLcd, @RequestParam String title, @RequestParam String description, @RequestParam LocalDate date, Model model){
        News news = News.builder().title(title).date(date).lcd(lcdService.findById(idLcd)).description(description).build();
        newsServiceImpl.saveEntity(news);
        return "redirect:/lcd_edit/"+idLcd;
    }

    @PostMapping("/delete_news")
    public String deleteNews(@RequestParam int idNews, @RequestParam int idLcd, Model model){
        newsServiceImpl.deleteById(idNews);
        return "redirect:/lcd_edit/"+idLcd;
    }

}
