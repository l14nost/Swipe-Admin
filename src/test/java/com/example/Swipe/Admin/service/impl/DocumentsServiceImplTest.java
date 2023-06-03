package com.example.Swipe.Admin.service.impl;

import com.example.Swipe.Admin.entity.Documents;
import com.example.Swipe.Admin.entity.LCD;
import com.example.Swipe.Admin.repository.DocumentsRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DocumentsServiceImplTest {
    @Mock
    private DocumentsRepo documentsRepo;
    @InjectMocks
    private DocumentsServiceImpl documentsService;

    @Test
    void findAll() {
        List<Documents> documents = new ArrayList<>();
        for (int i = 0 ;i<5;i++) {
            documents.add(new Documents());
        }
        when(documentsRepo.findAll()).thenReturn(documents);
        List<Documents> documents1 = documentsService.findAll();
        assertEquals(5,documents1.size());
    }

    @Test
    void findById() {
        Documents documents = Documents.builder()
                .idDocuments(5)
                .lcd(LCD.builder().idLcd(4).build())
                .fileName("../uploads/76ac2969-de7a-4885-9299-08b9c4dfe9e3-swipe-final.mwb")
                .name("swipe-fix.mwb")
                .build();
        Documents documentsFake = Documents.builder()
                .idDocuments(4)
                .lcd(LCD.builder().idLcd(4).build())
                .fileName("../uploads/76ac2969-de7a-4885-9299-08b9c4dfe9e3-swipe-final.mwb")
                .name("swipe-fi.mwb")
                .build();
        when(documentsRepo.findById(5)).thenReturn(Optional.of(documents));
        Documents documents1 = documentsService.findById(5);
        assertEquals(documents1, documents);
        verify(documentsRepo,times(1)).findById(5);
    }
    @Test
    void findByIdNotFound() {
        when(documentsRepo.findById(1)).thenReturn(Optional.empty());
        assertNull(documentsService.findById(1));
    }
    @Test
    void saveEntity() {
        Documents documentsFake = Documents.builder()
                .idDocuments(1)
                .lcd(LCD.builder().idLcd(4).build())
                .fileName("../uploads/76ac2969-de7a-4885-9299-08b9c4dfe9e3-swipe-final.mwb")
                .name("swipe-fi.mwb")
                .build();
        documentsService.saveEntity(documentsFake);
        verify(documentsRepo).save(documentsFake);
    }

    @Test
    void deleteById() {
        documentsService.deleteById(5);
        verify(documentsRepo).deleteById(5);
    }

    @Test
    void updateEntity() {
        Documents documents = Documents.builder()
                .fileName("")
                .idDocuments(1)
                .build();
        when(documentsRepo.findById(1)).thenReturn(Optional.of(documents));
        Documents documentsUpdate = Documents.builder()
                .name("Name")
                .fileName("Filename")
                .lcd(LCD.builder().idLcd(1).build())
                .build();
        documentsService.updateEntity(documentsUpdate,1);
        Documents documentsSave = Documents.builder()
                .idDocuments(1)
                .name("Name")
                .fileName("Filename")
                .lcd(LCD.builder().idLcd(1).build())
                .build();
        verify(documentsRepo).saveAndFlush(documentsSave);
    }
}