package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.service.DocumentsService;
import com.example.Swipe.Admin.service.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class DocumentsController {
    private final DocumentsService documentsService;

    public DocumentsController(DocumentsService documentsService) {
        this.documentsService = documentsService;
    }
}
