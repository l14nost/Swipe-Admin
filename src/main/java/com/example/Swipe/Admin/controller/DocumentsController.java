package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.entity.Documents;
import com.example.Swipe.Admin.service.impl.DocumentsServiceImpl;
import com.example.Swipe.Admin.service.impl.LCDServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class DocumentsController {
    private final DocumentsServiceImpl documentsServiceImpl;
    private final LCDServiceImpl lcdService;



    @GetMapping("/add_document/{idLcd}")
    public String addDocument(@PathVariable int idLcd, Model model){
        Documents documents = Documents.builder().lcd(lcdService.findById(idLcd)).name("").fileName("../admin/dist/img/document.png").build();
        documentsServiceImpl.saveEntity(documents);
        return "redirect:/lcd_edit/"+idLcd;
    }
    @PostMapping("/delete_document")
    public String deleteDocument(@RequestParam int idDocument,@RequestParam int idLcd, Model model){
        documentsServiceImpl.deleteById(idDocument);
        return "redirect:/lcd_edit/"+idLcd;
    }
}
