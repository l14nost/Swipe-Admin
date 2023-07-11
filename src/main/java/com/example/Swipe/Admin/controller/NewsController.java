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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class NewsController {
    private Logger log = LoggerFactory.getLogger(NewsController.class);
    private final NewsServiceImpl newsServiceImpl;
    private final LCDServiceImpl lcdService;


    @GetMapping("/news_page")
    public String blackListPage(
            @RequestParam(name = "pageBlackList", defaultValue = "0", required = false)int pageBlackList,
            @RequestParam(name = "sizeBlackList", defaultValue = "10", required = false)int sizeBlackList,
            @RequestParam(name = "search", required = false, defaultValue = "null") String keyWord,
            @RequestParam(name = "sortedBy", required = false, defaultValue = "idNews") String sortedBy,
            @RequestParam(name = "order", required = false, defaultValue = "1") int order,
            Model model){
//        log.info("Current page:"+pageBlackList+", size:"+sizeBlackList);
        Pageable pageable = PageRequest.of(pageBlackList,sizeBlackList);
        model.addAttribute("searchV", keyWord);
        model.addAttribute("blackList", newsServiceImpl.pagination(pageable,keyWord,sortedBy,order));
        model.addAttribute("size", sizeBlackList);
        model.addAttribute("allSize",newsServiceImpl.count());
        model.addAttribute("currentPage",pageBlackList);
        model.addAttribute("sort",sortedBy);
        model.addAttribute("order",order);
        return "admin/news_main";
    }


    @GetMapping("/edit_news/{idNews}")
    public String editNews(@PathVariable int idNews, Model model){
        NewsDTO newsDTO = newsServiceImpl.findByIdDTO(idNews);
        model.addAttribute("news", newsDTO);
        model.addAttribute("idLcd", newsDTO.getIdLcd());
        model.addAttribute("lcds", lcdService.findAll());
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
//    @PostMapping("/edit_news/{idNews}")
//    public String updateNews(@PathVariable int idNews, @ModelAttribute(name = "news") @Valid NewsDTO newsDTO,BindingResult result, Model model){
//        if (LocalDate.now().isBefore(newsDTO.getDate())){
//            result.addError(new FieldError("news", "date", "Некоректная дата"));
//        }
//        if (result.hasErrors()){
//            System.out.println(result.getAllErrors());
//            model.addAttribute("news", newsDTO);
//            model.addAttribute("idLcd", newsDTO.getIdLcd());
//            return "admin/news_edit";
//        }
//        newsServiceImpl.updateDTO(newsDTO,idNews);
//        int lcd = newsServiceImpl.findById(idNews).getLcd().getIdLcd();
//        log.info("News id:"+idNews+", for lcd:"+lcd+", was update");
//        return "redirect:/lcd_edit/"+lcd;
//    }
    @PostMapping("/edit_news/{idNews}")
    public String updateNews(@PathVariable int idNews, @ModelAttribute(name = "news") @Valid NewsDTO newsDTO,BindingResult result, Model model){
        if (LocalDate.now().isBefore(newsDTO.getDate())){
            result.addError(new FieldError("news", "date", "Некоректная дата"));
        }
        if (result.hasErrors()){
            System.out.println(result.getAllErrors());
            model.addAttribute("news", newsDTO);
            model.addAttribute("lcds", lcdService.findAll());
            model.addAttribute("idLcd", newsDTO.getIdLcd());
            return "admin/news_edit";
        }
        System.out.println(newsDTO.getIdLcd());
        newsServiceImpl.updateDTO(newsDTO,idNews);
        int lcd = newsServiceImpl.findById(idNews).getLcd().getIdLcd();
        log.info("News id:"+idNews+", for lcd:"+lcd+", was update");
        return "redirect:/news_page";
    }

    @GetMapping("/add_news/{idLcd}")
    public String addNews(@PathVariable int idLcd, Model model){
        model.addAttribute("idLcd", idLcd);
        model.addAttribute("news", NewsDTO.builder().build());
        return "admin/news_add";
    }
    @GetMapping("/add_news")
    public String addNewsPage( Model model){
        model.addAttribute("lcds", lcdService.findAll());
        model.addAttribute("news", NewsDTO.builder().build());
        return "admin/news_add1";
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
        if (newsDTO.getDate()!=null) {
            if (LocalDate.now().isBefore(newsDTO.getDate())) {
                bindingResult.addError(new FieldError("news", "date", "Некоректная дата"));
            }
        }
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
    @PostMapping("/add_news")
    public String saveNews1(@ModelAttribute(name = "news") @Valid NewsDTO newsDTO, BindingResult bindingResult, Model model){
        if (newsDTO.getDate()!=null) {
            if (LocalDate.now().isBefore(newsDTO.getDate())) {
                bindingResult.addError(new FieldError("news", "date", "Некоректная дата"));
            }
        }
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
            model.addAttribute("lcds", lcdService.findAll());
            model.addAttribute("news",newsDTO);
            return "admin/news_add1";
        }
        newsServiceImpl.saveDTO(newsDTO);
        log.info("News"+", for lcd:"+newsDTO.getIdLcd()+", was add");
        return "redirect:/news_page";
    }

//    @PostMapping("/delete_news")
//    public String deleteNews(@RequestParam int idNews, @RequestParam int idLcd, Model model){
//        newsServiceImpl.deleteById(idNews);
//        log.info("News id:"+idNews+", for lcd:"+idLcd+", was delete");
//        return "redirect:/lcd_edit/"+idLcd;
//    }
    @PostMapping("/delete_news")
    public String deleteNews(@RequestParam int idNews, Model model){
        newsServiceImpl.deleteById(idNews);
        log.info("News id:"+idNews+", was delete");
        return "redirect:/news_page";
    }
}
