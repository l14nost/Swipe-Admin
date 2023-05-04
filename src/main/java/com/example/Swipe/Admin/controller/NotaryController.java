package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.entity.Agent;
import com.example.Swipe.Admin.entity.Notary;
import com.example.Swipe.Admin.service.impl.NotaryServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NotaryController {
    private final NotaryServiceImpl notaryServiceImpl;

    public NotaryController(NotaryServiceImpl notaryServiceImpl) {
        this.notaryServiceImpl = notaryServiceImpl;
    }
    @GetMapping("/notary_edit/{id}")
    public String notaryEdit(@PathVariable int id, Model model){
        model.addAttribute("notary",notaryServiceImpl.findById(id));
        return "admin/notary_edit";
    }
    @PostMapping("/notary_update/{id}")
    public String notaryUpdate(@PathVariable int id, @RequestParam String name, @RequestParam String surname, @RequestParam String number, @RequestParam String mail, Model model){
        Notary notary = Notary.builder().surname(surname).name(name).number(number).mail(mail).build();
        notaryServiceImpl.updateEntity(notary,id);
        return "redirect:/users";
    }
}
