package com.example.Swipe.Admin.service.impl;

import com.example.Swipe.Admin.entity.LCD;
import com.example.Swipe.Admin.entity.Photo;
import com.example.Swipe.Admin.repository.PhotosRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PhotosServiceImplTest {

    @Mock
    private PhotosRepo photosRepo;
    @InjectMocks
    private PhotosServiceImpl photosService;
    @Test
    void findAll() {
        List<Photo> photos = Arrays.asList(
                Photo.builder().build(),
                Photo.builder().build(),
                Photo.builder().build(),
                Photo.builder().build()
        );
        when(photosRepo.findAll()).thenReturn(photos);
        List<Photo> photos1 = photosService.findAll();
        assertEquals(4,photos1.size());
    }

    @Test
    void findById() {
        Photo photo = Photo.builder()
                .idPhotos(1)
                .fileName("")
                .lcd(LCD.builder().idLcd(1).build())
                .build();
        when(photosRepo.findById(1)).thenReturn(Optional.of(photo));
        Photo photo1 = photosService.findById(1);
        assertEquals("",photo1.getFileName());
        assertEquals(1,photo1.getIdPhotos());


    }
    @Test
    void findByIdNull() {
        Photo photo1 = photosService.findById(1);
        assertNull(photo1);
    }

    @Test
    void saveEntity() {
        Photo photo = Photo.builder()
                .fileName("")
                .lcd(LCD.builder().idLcd(1).build())
                .build();
        Photo photoFake = Photo.builder()
                .fileName("1")
                .lcd(LCD.builder().idLcd(1).build())
                .build();
        photosService.saveEntity(photo);
        verify(photosRepo).save(photo);
    }
    //перед тестом добавить файл с названием "804059ae-eba1-49a0-8e06-ccf3f6b545fa-stock2.jpg"
    @Test
    void deleteById() {
        Photo photo = Photo.builder()
                .fileName("../uploads/804059ae-eba1-49a0-8e06-ccf3f6b545fa-stock2.jpg")
                .lcd(LCD.builder().idLcd(1).build())
                .build();
        when(photosRepo.findById(1)).thenReturn(Optional.of(photo));
        photosService.setUpload("/C:/Users/Amir Banov/IdeaProjects/Swipe-Admin/uploads/");
        photosService.deleteById(1);
        verify(photosRepo).deleteById(1);

    }

    @Test
    void updateEntity() {
        Photo photo = Photo.builder()
                .idPhotos(1)
                .fileName("../uploads/804059ae-eba1-49a0-8e06-ccf3f6b545fa-stock2.jpg")
                .lcd(LCD.builder().idLcd(1).build())
                .build();
        when(photosRepo.findById(1)).thenReturn(Optional.of(photo));
        Photo photoUpdate = Photo.builder()
                .fileName("../uploads/804059ae-eba1-49a0-8e06-ccf3f6b545fa-stock3.jpg")
                .build();
        photosService.updateEntity(photoUpdate,1);
        Photo photoSave = Photo.builder()
                .idPhotos(1)
                .fileName("../uploads/804059ae-eba1-49a0-8e06-ccf3f6b545fa-stock3.jpg")
                .lcd(LCD.builder().idLcd(1).build())
                .build();
        verify(photosRepo).saveAndFlush(photoSave);
    }
}