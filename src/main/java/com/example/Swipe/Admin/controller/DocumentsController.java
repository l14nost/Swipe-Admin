package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.service.impl.DocumentsServiceImpl;
import org.springframework.stereotype.Controller;

@Controller
public class DocumentsController {
    private final DocumentsServiceImpl documentsServiceImpl;

    public DocumentsController(DocumentsServiceImpl documentsServiceImpl) {
        this.documentsServiceImpl = documentsServiceImpl;
    }
}
