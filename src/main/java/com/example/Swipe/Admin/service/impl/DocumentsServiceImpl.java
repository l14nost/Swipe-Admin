package com.example.Swipe.Admin.service.impl;

import com.example.Swipe.Admin.entity.Documents;
import com.example.Swipe.Admin.repository.DocumentsRepo;
import com.example.Swipe.Admin.service.DocumentsService;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Setter
public class DocumentsServiceImpl implements DocumentsService {
    private Logger log = LoggerFactory.getLogger(DocumentsServiceImpl.class);
    private final DocumentsRepo documentsRepo;
    @Value("${upload.path}")
    private String upload;

    public DocumentsServiceImpl(DocumentsRepo documentsRepo) {
        this.documentsRepo = documentsRepo;
    }

    @Override
    public List<Documents> findAll() {
        return documentsRepo.findAll();
    }

    @Override
    public Documents findById(int id) {
        Optional<Documents> documents = documentsRepo.findById(id);
        if(documents.isPresent()){
            return documents.get();
        }
        else {
            return null;
        }
    }

    @Override
    public void saveEntity(Documents documents) {
        documentsRepo.save(documents);
    }

    @Override
    public void deleteById(int id) {
        Documents documents = findById(id);
        if (!documents.getFileName().equals("../admin/dist/img/document.jpg")) {
            String fileNameDelete = documents.getFileName().substring(11, documents.getFileName().length());
            File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
            fileDelete.delete();
        }
        documentsRepo.deleteById(id);
    }

    @Override
    public void updateEntity(Documents documents, int id) {
        Optional<Documents> documentsOptional = documentsRepo.findById(id);
        if(documentsOptional.isPresent()){
            Documents documentsUpdate = documentsOptional.get();
            if(documents.getName()!=null){
                documentsUpdate.setName(documents.getName());
            }
            if(documents.getFileName()!=null){
                documentsUpdate.setFileName(documents.getFileName());
            }
            documentsRepo.saveAndFlush(documentsUpdate);
        }
    }
}
