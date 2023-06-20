package com.example.Swipe.Admin.service.impl;

import com.example.Swipe.Admin.entity.Documents;
import com.example.Swipe.Admin.repository.DocumentsRepo;
import com.example.Swipe.Admin.service.DocumentsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentsServiceImpl implements DocumentsService {
    private Logger log = LoggerFactory.getLogger(DocumentsServiceImpl.class);
    private final DocumentsRepo documentsRepo;

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
