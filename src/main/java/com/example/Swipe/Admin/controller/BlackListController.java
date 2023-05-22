package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.entity.User;
import com.example.Swipe.Admin.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class BlackListController {
    private final UserServiceImpl userService;

    @GetMapping("/black_list")
    public String blackListPage(
            @RequestParam(name = "pageBlackList", defaultValue = "0", required = false)int pageBlackList,
            @RequestParam(name = "sizeBlackList", defaultValue = "3", required = false)int sizeBlackList,
            @RequestParam(name = "search", required = false, defaultValue = "null") String keyWord,
            Model model){
        System.out.println(keyWord);
        Pageable pageable = PageRequest.of(pageBlackList,sizeBlackList);
        model.addAttribute("searchV", keyWord);
        model.addAttribute("blackList", userService.blackList(pageable,keyWord));
        model.addAttribute("size", sizeBlackList);
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
        return "redirect:/users";
    }
    @PostMapping("/delete_from_black_list")
    public String deleteFromBlackList(@RequestParam int idUser, Model model){
        User user = userService.findById(idUser);
        user.setBlackList(false);
        userService.updateEntity(user,idUser);
        return "redirect:/black_list";
    }
}
