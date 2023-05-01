package com.example.Swipe.Admin.service;

import com.example.Swipe.Admin.repository.DocumentsRepo;
import org.springframework.stereotype.Service;

@Service
public class DocumentsService {
    private final DocumentsRepo documentsRepo;

    public DocumentsService(DocumentsRepo documentsRepo) {
        this.documentsRepo = documentsRepo;
    }
}
