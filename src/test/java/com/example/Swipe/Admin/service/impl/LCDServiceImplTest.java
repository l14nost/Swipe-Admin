package com.example.Swipe.Admin.service.impl;

import com.example.Swipe.Admin.dto.DocumentDTO;
import com.example.Swipe.Admin.dto.LcdDTO;
import com.example.Swipe.Admin.dto.PhotoDTO;
import com.example.Swipe.Admin.entity.Documents;
import com.example.Swipe.Admin.entity.LCD;
import com.example.Swipe.Admin.entity.Photo;
import com.example.Swipe.Admin.entity.User;
import com.example.Swipe.Admin.enums.*;
import com.example.Swipe.Admin.repository.LCDRepo;
import com.example.Swipe.Admin.specification.LcdSpecification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LCDServiceImplTest {
    @Mock
    private PhotosServiceImpl photosService;
    @Mock
    private DocumentsServiceImpl documentsService;
    @Mock
    private LCDRepo lcdRepo;
    @Mock
    private UserServiceImpl userService;
    @InjectMocks
    private LCDServiceImpl lcdService;
    @Test
    void findAllPagination() {
        List<LCD> lcds = Arrays.asList(
                LCD.builder().build(),
                LCD.builder().build(),
                LCD.builder().build()
        );
        Pageable pageable = PageRequest.of(0,3);
        LcdSpecification lcdSpecification1 = LcdSpecification.builder().sort("idLcd").build();
        when(lcdRepo.findAll(lcdSpecification1,pageable)).thenReturn(new PageImpl<>(lcds));
        Page<LcdDTO> lcdDTOs = lcdService.findAllPagination(pageable,"null","idLcd");
        assertEquals(3,lcdDTOs.getContent().size());
        List<LCD> lcdSpecificationList = Arrays.asList(
                LCD.builder().name("LcdName").build(),
                LCD.builder().name("LcdName").build()
        );
        Pageable pageableSpecification = PageRequest.of(0,3);
        LcdSpecification lcdSpecification = LcdSpecification.builder().keyWord("LcdName").sort("idLcd").build();
        when(lcdRepo.findAll(lcdSpecification,pageable)).thenReturn(new PageImpl<>(lcdSpecificationList));
        Page<LcdDTO> lcdCheck = lcdService.findAllPagination(pageableSpecification,"LcdName","idLcd");
        assertEquals(2,lcdCheck.getContent().size());
    }

    @Test
    void findAll() {
        List<LCD> lcds = Arrays.asList(
                LCD.builder().build(),
                LCD.builder().build(),
                LCD.builder().build()
        );
        when(lcdRepo.findAll()).thenReturn(lcds);
        List<LCD> lcdList = lcdService.findAll();
        assertEquals(3,lcdList.size());

    }

    @Test
    void findByIdDTO() {
        LCD lcd = LCD.builder()
                .idLcd(1)
                .name("Name")
                .type(LCDType.FIVE)
                .heating(HeatingType.AUTONOMOUS)
                .build();
        when(lcdRepo.findById(1)).thenReturn(Optional.of(lcd));
        LcdDTO lcdDTO = lcdService.findByIdDTO(1);
        assertEquals("Name",lcdDTO.getName());
        assertEquals(LCDType.FIVE,lcdDTO.getType());
        assertEquals(HeatingType.AUTONOMOUS,lcdDTO.getHeating());
        assertNull(lcdService.findByIdDTO(2));
    }

    @Test
    void findById() {
        LCD lcd = LCD.builder()
                .idLcd(1)
                .name("Name")
                .type(LCDType.FIVE)
                .heating(HeatingType.AUTONOMOUS)
                .build();
        when(lcdRepo.findById(1)).thenReturn(Optional.of(lcd));
        LCD lcdDTO = lcdService.findById(1);
        assertEquals("Name",lcdDTO.getName());
        assertEquals(LCDType.FIVE,lcdDTO.getType());
        assertEquals(HeatingType.AUTONOMOUS,lcdDTO.getHeating());
        assertNull(lcdService.findById(2));
    }

    @Test
    void saveDTO() {
        User user = User.builder()
                .idUser(1)
                .number("123123123")
                .mail("123@gmail.com")
                .name("User")
                .surname("Surname")
                .blackList(false)
                .role(Role.USER)
                .build();
        when(userService.findById(1)).thenReturn(user);
        LcdDTO lcdDTO = LcdDTO.builder()
                .contractor(1)
                .name("Name")
                .lcdClass(ClassType.MASS)
//                .file(new MockMultipartFile("file","example.txt","text/plain","Hello World".getBytes()))
                .build();
        lcdService.setUpload("/C:/Users/Amir Banov/IdeaProjects/Swipe-Admin/uploads/");
        lcdService.saveDTO(lcdDTO);
        LCD lcd = LCD.builder()
                .name("Name")
                .lcdClass(ClassType.MASS)
                .mainPhoto("../admin/dist/img/default.jpg")
                .user(user)
                .build();
        verify(lcdRepo).save(lcd);
    }
    @Test
    void saveDTO_fileNull() {
        User user = User.builder()
                .idUser(1)
                .number("123123123")
                .mail("123@gmail.com")
                .name("User")
                .surname("Surname")
                .blackList(false)
                .role(Role.USER)
                .build();
        when(userService.findById(1)).thenReturn(user);
        LcdDTO lcdDTO = LcdDTO.builder()
                .contractor(1)
                .name("Name")
                .lcdClass(ClassType.MASS)
                .build();
        lcdService.saveDTO(lcdDTO);
        LCD lcd = LCD.builder()
                .name("Name")
                .lcdClass(ClassType.MASS)
                .mainPhoto("../admin/dist/img/default.jpg")
                .user(user)
                .build();
        verify(lcdRepo).save(lcd);
    }
    @Test
    void saveDTO_fileEmpty() {
        User user = User.builder()
                .idUser(1)
                .number("123123123")
                .mail("123@gmail.com")
                .name("User")
                .surname("Surname")
                .blackList(false)
                .role(Role.USER)
                .build();
        when(userService.findById(1)).thenReturn(user);
        LcdDTO lcdDTO;
            lcdDTO = LcdDTO.builder()
                    .contractor(1)
                    .name("Name")
                    .lcdClass(ClassType.MASS)
//                    .file(new MockMultipartFile("file","example.txt","text/plain",new ByteArrayResource(new byte[0]).getInputStream()))
                    .build();

        lcdService.setUpload("/C:/Users/Amir Banov/IdeaProjects/Swipe-Admin/uploads/");
        lcdService.saveDTO(lcdDTO);
        LCD lcd = LCD.builder()
                .name("Name")
                .lcdClass(ClassType.MASS)
                .mainPhoto("../admin/dist/img/default.jpg")
                .user(user)
                .build();
        verify(lcdRepo).save(lcd);
    }

    @Test
    void saveEntity() {
        LCD lcd = LCD.builder()
                .name("Name")
                .lcdClass(ClassType.MASS)
                .mainPhoto("../admin/dist/img/default.jpg")
                .build();
        lcdService.saveEntity(lcd);
        verify(lcdRepo).save(lcd);
    }

    @Test
    void deleteById() {
        lcdService.deleteById(1);
        verify(lcdRepo).deleteById(1);
    }

    @Test
    void updateEntity() {
        LCD lcd =LCD.builder()
                .idLcd(1)
                .name("Name")
                .description("Description")
                .sumContract("Sum")
                .photoList(List.of(Photo.builder().build()))
                .build();
        when(lcdRepo.findById(1)).thenReturn(Optional.of(lcd));
        LCD lcdUpdate = LCD.builder()
                .name("Name")
                .description("Description1")
                .sumContract("Sum1")
                .photoList(List.of(Photo.builder().idPhotos(1).build()))
                .advantages("")
                .appointment("")
                .communal(CommunalType.HALF)
                .gas(GasType.NO)
                .heating(HeatingType.AUTONOMOUS)
                .sewerage(HeatingType.CENTRAL)
                .status(StatusLCDType.APARTMENT)
                .technology(TechnologyType.PANEL)
                .mainPhoto("")
                .distanceSea(1)
                .height(1)
                .territory(TerritoryType.CLOSE)
                .address("г.Город, р.Район, вул.Вулиця,1")
                .type(LCDType.FIVE)
                .typePayment("")
                .waterSupply(HeatingType.AUTONOMOUS)
                .lcdClass(ClassType.MASS)
                .formalization("")
                .user(User.builder().build())
                .build();
        lcdService.updateEntity(lcdUpdate,1);
        LCD lcdSave = LCD.builder()
                .idLcd(1)
                .name("Name")
                .description("Description1")
                .sumContract("Sum1")
                .photoList(List.of(Photo.builder().idPhotos(1).build()))
                .advantages("")
                .appointment("")
                .communal(CommunalType.HALF)
                .address("г.Город, р.Район, вул.Вулиця,1")
                .gas(GasType.NO)
                .heating(HeatingType.AUTONOMOUS)
                .sewerage(HeatingType.CENTRAL)
                .status(StatusLCDType.APARTMENT)
                .technology(TechnologyType.PANEL)
                .mainPhoto("")
                .distanceSea(1)
                .height(1)
                .territory(TerritoryType.CLOSE)
                .type(LCDType.FIVE)
                .typePayment("")
                .waterSupply(HeatingType.AUTONOMOUS)
                .lcdClass(ClassType.MASS)
                .user(User.builder().build())
                .formalization("")
                .build();
        verify(lcdRepo).saveAndFlush(lcdSave);
    }

    @Test
    void updateDTO() {
        LCD lcd =LCD.builder()
                .idLcd(1)
                .name("Name")
                .photoList(List.of(Photo.builder().idPhotos(1).fileName("../uploads/1d9c952a-152e-4678-8bfe-1030efe3a953-example.txt").build()))
                .documents(List.of(Documents.builder().idDocuments(1).fileName("../uploads/2c8c50b0-6e0f-4d3c-a3db-69e64a41d167-example.txt").build()))
                .mainPhoto("../uploads/4d4c3700-6f63-4730-b88c-4ae57241c326-example.txt")
                .description("Description")
                .sumContract("Sum")
                .build();
        when(lcdRepo.findById(1)).thenReturn(Optional.of(lcd));
        LcdDTO lcdUpdate = LcdDTO.builder()
                .name("Name1")
                .description("Description1")
                .sumContract("Sum1")

//                .gallery(List.of(new MockMultipartFile("file","example.txt","text/plain","Hello World".getBytes())))
//                .documentsFiles(List.of(new MockMultipartFile("file","example.txt","text/plain","Hello World".getBytes())))
                .photoList(List.of(PhotoDTO.builder().idPhotos(1).fileGallery(new MockMultipartFile("file","example.txt","text/plain","Hello World".getBytes())).build()))
                .documents(List.of(DocumentDTO.builder().idDocuments(1).fileDocument(new MockMultipartFile("file","example.txt","text/plain","Hello World".getBytes())).build()))
                .advantages("")
                .appointment("")
                .communal(CommunalType.HALF)
                .gas(GasType.NO)
                .address("г.Город, р.Район, вул.Вулиця,1")
                .heating(HeatingType.AUTONOMOUS)
                .sewerage(HeatingType.CENTRAL)
                .status(StatusLCDType.APARTMENT)
                .technology(TechnologyType.PANEL)
//                .file(new MockMultipartFile("file","example.txt","text/plain","Hello World".getBytes()))
                .distanceSea(1)
                .height(1)
                .territory(TerritoryType.CLOSE)
                .type(LCDType.FIVE)
                .typePayment("")
                .waterSupply(HeatingType.AUTONOMOUS)
                .lcdClass(ClassType.MASS)
                .formalization("")
                .contractor(12)
                .build();
        lcdService.setUpload("/C:/Users/Amir Banov/IdeaProjects/Swipe-Admin/uploads/");
        lcdService.updateDTO(lcdUpdate,1);
        LCD lcdSave = LCD.builder()
                .idLcd(1)
                .name("Name1")
                .description("Description1")
                .sumContract("Sum1")
                .photoList(List.of(Photo.builder().idPhotos(1).build()))
                .documents(List.of(Documents.builder().idDocuments(1).build()))
                .advantages("")
                .appointment("")
                .communal(CommunalType.HALF)
                .gas(GasType.NO)
                .heating(HeatingType.AUTONOMOUS)
                .sewerage(HeatingType.CENTRAL)
                .address("г.Город, р.Район, вул.Вулиця,1")
                .status(StatusLCDType.APARTMENT)
                .technology(TechnologyType.PANEL)
                .distanceSea(1)
                .height(1)
                .territory(TerritoryType.CLOSE)
                .type(LCDType.FIVE)
                .typePayment("")
                .waterSupply(HeatingType.AUTONOMOUS)
                .lcdClass(ClassType.MASS)
                .formalization("")
                .user(User.builder().idUser(12).build())
                .build();
        verify(lcdRepo).saveAndFlush(lcdSave);
    }
}