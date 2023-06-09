package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.entity.Documents;
import com.example.Swipe.Admin.service.impl.DocumentsServiceImpl;
import com.example.Swipe.Admin.service.impl.LCDServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DocumentsController {
    private Logger log = LoggerFactory.getLogger(DocumentsController.class);
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
        log.info("Document id:"+idDocument+", was delete");
        return "redirect:/lcd_edit/"+idLcd;
    }


//    @PostMapping("/delete_document_list")
//    public String deleteDocument1(@RequestParam List<Integer> idDocuments, @RequestParam int idLcd, Model model){
////        documentsServiceImpl.deleteById(idDocument);
//        System.out.println("+++++"+idDocuments);
//        log.info("Document id:"+idDocuments+", was delete");
//        return "redirect:/lcd_edit/"+idLcd;
//    }
}
