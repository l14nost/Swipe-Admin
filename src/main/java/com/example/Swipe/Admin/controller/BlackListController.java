package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.entity.User;
import com.example.Swipe.Admin.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
public class BlackListController {
    private Logger log = LoggerFactory.getLogger(BlackListController.class);
    private final UserServiceImpl userService;
    @GetMapping("/black_list")
    public String blackListPage(
            @RequestParam(name = "pageBlackList", defaultValue = "0", required = false)int pageBlackList,
            @RequestParam(name = "sizeBlackList", defaultValue = "3", required = false)int sizeBlackList,
            @RequestParam(name = "search", required = false, defaultValue = "null") String keyWord,
            @RequestParam(name = "sortedBy", required = false, defaultValue = "idUser") String sortedBy,
            @RequestParam(name = "order", required = false, defaultValue = "1") int order,
            Model model){
        System.out.println(keyWord);
        System.out.println(sortedBy);
        log.info("Current page:"+pageBlackList+", size:"+sizeBlackList);
        Pageable pageable = PageRequest.of(pageBlackList,sizeBlackList);
        System.out.println(userService.blackList(pageable,keyWord,sortedBy).getTotalPages());
        model.addAttribute("searchV", keyWord);
        model.addAttribute("blackList", userService.blackList(pageable,keyWord,sortedBy,order));
        model.addAttribute("size", sizeBlackList);
        model.addAttribute("allSize",userService.countBlackList());
        model.addAttribute("currentPage",pageBlackList);
        model.addAttribute("sort",sortedBy);
        return "admin/black_list";
    }
//    @GetMapping("/black_list/search/{name}")
//    public String searchByName(@PathVariable String name,Model model){
//        model.addAttribute("blackList", userService.specificationForBlackList(name,PageRequest.of(0,3)));
//        return "admin/black_list";
//    }

    @PostMapping("/add_to_black_list")
    public String addToBlackList(@RequestParam int idUser, Model model){
        User user = userService.findById(idUser);
        user.setBlackList(true);
        userService.updateEntity(user,idUser);
        log.info("User "+ idUser+" add to blacklist");
        return "redirect:/users";
    }
    @PostMapping("/delete_from_black_list")
    public String deleteFromBlackList(@RequestParam int idUser, Model model){
        User user = userService.findById(idUser);
        user.setBlackList(false);
        userService.updateEntity(user,idUser);
        log.info("User "+ idUser+" delete from blacklist");
        return "redirect:/black_list";
    }
}
