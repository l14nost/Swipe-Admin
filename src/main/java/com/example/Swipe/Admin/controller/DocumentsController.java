package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.entity.Documents;
import com.example.Swipe.Admin.service.impl.ContractorServiceImpl;
import com.example.Swipe.Admin.service.impl.DocumentsServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DocumentsController {
    private final DocumentsServiceImpl documentsServiceImpl;
    private final ContractorServiceImpl contractorService;

    public DocumentsController(DocumentsServiceImpl documentsServiceImpl, ContractorServiceImpl contractorService) {
        this.documentsServiceImpl = documentsServiceImpl;
        this.contractorService = contractorService;

    }
    @GetMapping("/add_document/{id}")
    public String addDocument(@PathVariable int id, Model model){
//        Documents documents = Documents.builder().contractor(contractorService.findById(id)).name("").fileName("../admin/dist/img/default.jpg").build();
//        documentsServiceImpl.saveEntity(documents);
        return "redirect:/contractor_edit/"+id;
    }
    @GetMapping("/delete_document/{id}")
    public String deleteDocument(@PathVariable int id, Model model){
        documentsServiceImpl.deleteById(id);
        return "redirect:/contractor_edit/"+id;
    }
}
