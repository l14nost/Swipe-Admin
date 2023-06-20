package com.example.Swipe.Admin.service.impl;

import com.example.Swipe.Admin.dto.ApartmentDTO;
import com.example.Swipe.Admin.dto.PhotoDTO;
import com.example.Swipe.Admin.entity.*;
import com.example.Swipe.Admin.enums.*;
import com.example.Swipe.Admin.repository.ApartmentRepo;
import com.example.Swipe.Admin.specification.ApartmentForFrameSpecification;
import com.example.Swipe.Admin.specification.ApartmentForLcdSpecification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApartmentServiceImplTest {
    @Mock
    private  FrameServiceImpl frameService;
    @Mock
    private  PhotosServiceImpl photosService;
    @Mock
    private ApartmentRepo apartmentRepo;
    @InjectMocks
    private ApartmentServiceImpl apartmentService;
    @Test
    void findAll() {
        List<Apartment> apartments = new ArrayList<>();
        for (int i = 0;i<18;i++){
            apartments.add(Apartment.builder().build());
        }
        when(apartmentRepo.findAll()).thenReturn(apartments);
        List<Apartment> apartmentsList = apartmentService.findAll();
        assertEquals(18,apartmentsList.size());
    }

    @Test
    void findAllByFramePagination() {
        List<Apartment> apartmentsByFrame = new ArrayList<>();
        for (int i = 0;i<4;i++){
            apartmentsByFrame.add(Apartment.builder().build());
        }
        Pageable pageable = PageRequest.of(0,4);
        ApartmentForLcdSpecification apartmentForLcdSpecification = ApartmentForLcdSpecification.builder().sort("idApartment").build();
        when(apartmentRepo.findAll(eq(apartmentForLcdSpecification),eq(pageable))).thenReturn(new PageImpl<>(apartmentsByFrame));
        Page<ApartmentDTO> apartmentsList = apartmentService.findAllByFramePagination(pageable,0,"idApartment");
        assertEquals(4,apartmentsList.getContent().size());
    }
    @Test
    void findAllByFramePagination_Key() {
        List<Apartment> apartmentsByFrame = new ArrayList<>();
        for (int i = 0;i<4;i++){
            apartmentsByFrame.add(Apartment.builder().number(111).build());
        }
        Pageable pageable = PageRequest.of(0,4);
        ApartmentForLcdSpecification apartmentForLcdSpecification = ApartmentForLcdSpecification.builder().keyWord(111).build();
        when(apartmentRepo.findAll(eq(apartmentForLcdSpecification),eq(pageable))).thenReturn(new PageImpl<>(apartmentsByFrame));
        Page<ApartmentDTO> apartmentsList = apartmentService.findAllByFramePagination(pageable,111,"idApartment");
        assertEquals(4,apartmentsList.getContent().size());
    }

    @Test
    void findAllForFramePagination() {
        List<Apartment> apartmentsByFrame = new ArrayList<>();
        for (int i = 0;i<4;i++){
            apartmentsByFrame.add(Apartment.builder().frame(Frame.builder().idFrame(3).build()).build());
        }
        Pageable pageable = PageRequest.of(0,4);
        when(apartmentRepo.findAllByFrame(eq(Frame.builder().idFrame(3).build()),eq(pageable))).thenReturn(new PageImpl<>(apartmentsByFrame));
        Page<ApartmentDTO> apartmentsList = apartmentService.findAllForFramePagination(Frame.builder().idFrame(3).build(),pageable,0,"idApartment");
        assertEquals(4,apartmentsList.getContent().size());
    }
    @Test
    void findAllForFramePagination_Key() {
        List<Apartment> apartmentsByFrameKey = new ArrayList<>();
        for (int i = 0;i<2;i++){
            apartmentsByFrameKey.add(Apartment.builder().frame(Frame.builder().idFrame(3).build()).number(111).build());
        }
        Pageable pageable1 = PageRequest.of(0,2);
        ApartmentForFrameSpecification apartmentForFrameSpecification = ApartmentForFrameSpecification.builder().keyWord(111).frame(Frame.builder().idFrame(3).build()).build();
        when(apartmentRepo.findAll(eq(apartmentForFrameSpecification),eq(pageable1))).thenReturn(new PageImpl<>(apartmentsByFrameKey));
        Page<ApartmentDTO> apartmentsList1 = apartmentService.findAllForFramePagination(Frame.builder().idFrame(3).build(),pageable1,111,"idApartment");
        assertEquals(2,apartmentsList1.getContent().size());
    }

    @Test
    void findByIdDTO() {
        ApartmentDTO apartmentDTO = ApartmentDTO.builder()
                .idApartment(6)
                .number(122)
                .build();
        Apartment apartment = Apartment.builder()
                .idApartment(6)
                .number(122)
                .build();
        when(apartmentRepo.findById(6)).thenReturn(Optional.of(apartment));
        ApartmentDTO apartmentDTOTest = apartmentService.findByIdDTO(6);
        assertEquals(apartmentDTO.getNumber(),apartmentDTOTest.getNumber());

    }
    @Test
    void findById() {
        Apartment apartment = Apartment.builder()
                .idApartment(6)
                .number(122)
                .build();
        when(apartmentRepo.findById(6)).thenReturn(Optional.of(apartment));
        Apartment apartment1 = apartmentService.findById(6);
        assertEquals(apartment,apartment1);
    }
    @Test
    void findById_NotFound() {
        Apartment apartment1 = apartmentService.findById(1);
        assertNull(apartment1);
    }
    @Test
    void findByIdDTONotFound() {
        when(apartmentRepo.findById(1)).thenReturn(Optional.empty());
        ApartmentDTO apartmentDTO = apartmentService.findByIdDTO(1);
        assertNull(apartmentDTO);

    }

    @Test
    void saveDTO() {
        ApartmentDTO apartmentDTO = ApartmentDTO.builder()
                .number(500)
                .lcd(1)
                .user(1)
                .file(new MockMultipartFile("file","example.txt","text/plain","Hello World".getBytes()))
                .build();
        Apartment apartment = Apartment.builder()
                .number(500)
                .lcd(LCD.builder().idLcd(1).build())
                .user(User.builder().idUser(1).build())
                .build();
        apartmentService.setUpload("/C:/Users/Amir Banov/IdeaProjects/Swipe-Admin/uploads/");

        apartmentService.saveDTO(apartmentDTO);
        verify(apartmentRepo).save(apartment);

    }
    @Test
    void saveDTO_fileEmpty() {
        ApartmentDTO apartmentDTO;
        try {
            apartmentDTO = ApartmentDTO.builder()
                    .number(500)
                    .lcd(1)
                    .user(1)
                    .file(new MockMultipartFile("file","example.txt","text/plain",new ByteArrayResource(new byte[0]).getInputStream())).build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Apartment apartment = Apartment.builder()
                .number(500)
                .lcd(LCD.builder().idLcd(1).build())
                .user(User.builder().idUser(1).build())
                .build();
        apartmentService.setUpload("/C:/Users/Amir Banov/IdeaProjects/Swipe-Admin/uploads/");

        apartmentService.saveDTO(apartmentDTO);
        verify(apartmentRepo).save(apartment);

    }

    @Test
    void saveDTO_forFrame() {
        ApartmentDTO apartmentDTO = ApartmentDTO.builder()
                .number(500)
                .lcd(1)
                .frame(1)
                .user(1)
                .build();
        Apartment apartment = Apartment.builder()
                .number(500)
                .lcd(LCD.builder().idLcd(1).build())
                .user(User.builder().idUser(1).build())
                .build();
        when(frameService.findById(1)).thenReturn(Frame.builder().lcd(LCD.builder().user(User.builder().build()).build()).build());
        apartmentService.saveDTO(apartmentDTO);
        verify(apartmentRepo).save(apartment);

    }

    @Test
    void saveEntity() {
        Apartment apartment = Apartment.builder().build();
        apartmentService.saveEntity(apartment);
        verify(apartmentRepo).save(apartment);

    }

    @Test
    void deleteById() {
        apartmentService.deleteById(1);
        verify(apartmentRepo).deleteById(1);
    }

    @Test
    void updateEntity() {
        Apartment apartment = Apartment.builder()
                .idApartment(1)
                .number(500)
                .lcd(LCD.builder().idLcd(1).build())
                .user(User.builder().idUser(1).build())
                .mainPhoto("1aa309a3-8f0c-44a2-97ae-e5936ad1c8fd-example.txt")
                .build();
        when(apartmentRepo.findById(1)).thenReturn(Optional.of(apartment));
        Apartment apartmentUpdate = Apartment.builder()
                .number(450)
                .lcd(LCD.builder().idLcd(1).build())
                .user(User.builder().idUser(1).build())
                .mainPhoto("1aa309a3-8f0c-44a2-97ae-e5936ad1c8fd-example.txt")
                .photoList(List.of(Photo.builder().build()))
                .commission(Commission.K10)
                .calculation(Calculation.CAPITAL)
                .balconyType(BalconyType.NO)
                .address("г.Город, р.Район, вул.Вулиця,1")
                .description("Description")
                .foundingDocument(FoundingDocument.TREATY)
                .communicationType(CommunicationType.SMS)
                .countRoom(CountRoom.K1)
                .totalArea(1)
                .kitchenArea(1)
                .layout(LayoutType.STUDIO)
                .price(1)
                .state(State.REPAIR)
                .type(TypeApartment.APARTMENT)
                .user(User.builder().build())
                .build();
        apartmentService.updateEntity(apartmentUpdate,1);
        Apartment apartmentSave = Apartment.builder()
                .idApartment(1)
                .number(450)
                .lcd(LCD.builder().idLcd(1).build())
                .user(User.builder().idUser(1).build())
                .mainPhoto("1aa309a3-8f0c-44a2-97ae-e5936ad1c8fd-example.txt")
                .photoList(List.of(Photo.builder().build()))
                .commission(Commission.K10)
                .calculation(Calculation.CAPITAL)
                .balconyType(BalconyType.NO)
                .description("Description")
                .foundingDocument(FoundingDocument.TREATY)
                .communicationType(CommunicationType.SMS)
                .address("г.Город, р.Район, вул.Вулиця,1")
                .countRoom(CountRoom.K1)
                .totalArea(1)
                .kitchenArea(1)
                .layout(LayoutType.STUDIO)
                .price(1)
                .state(State.REPAIR)
                .type(TypeApartment.APARTMENT)
                .user(User.builder().build())
                .build();
        verify(apartmentRepo).saveAndFlush(apartmentSave);

    }

    @Test
    void updateDTO() {
        Apartment apartment = Apartment.builder()
                .idApartment(1)
                .number(500)
                .lcd(LCD.builder().idLcd(1).build())
                .user(User.builder().idUser(1).build())
                .mainPhoto("../uploads/5ac8d732-7acc-41be-ae38-656058499aa2-example.txt")
                .photoList(List.of(Photo.builder().fileName("../uploads/02bcb7e3-0688-48f9-a510-796833e0e0e2-example.txt").build()))
                .build();
        when(apartmentRepo.findById(1)).thenReturn(Optional.of(apartment));
        ApartmentDTO apartmentUpdate = ApartmentDTO.builder()
                .number(450)
                .file(new MockMultipartFile("file","example.txt","text/plain","Hello World".getBytes()))
                .galleryPhoto(List.of(new MockMultipartFile("file","example.txt","text/plain","Hello World".getBytes())))
                .lcd(1)
                .user(1)
                .mainPhoto("../uploads/1aa309a3-8f0c-44a2-97ae-e5936ad1c8fd-example.txt")
                .photoList(List.of(PhotoDTO.builder().build()))
                .commission(Commission.K10)
                .calculation(Calculation.CAPITAL)
                .balconyType(BalconyType.NO)
                .description("Description")
                .address("г.Город, р.Район, вул.Вулиця,1")
                .foundingDocument(FoundingDocument.TREATY)
                .communicationType(CommunicationType.SMS)
                .countRoom(CountRoom.K1)
                .totalArea(1)
                .heatingType(HeatingType.AUTONOMOUS)
                .kitchenArea(1)
                .layout(LayoutType.STUDIO)
                .price(1)
                .state(State.REPAIR)
                .type(TypeApartment.APARTMENT)
                .build();
        apartmentService.setUpload("/C:/Users/Amir Banov/IdeaProjects/Swipe-Admin/uploads/");
        try {
            apartmentService.updateDTO(apartmentUpdate,1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Apartment apartmentSave = Apartment.builder()
                .idApartment(1)
                .number(450)
                .lcd(LCD.builder().idLcd(1).build())
                .user(User.builder().idUser(1).build())
                .photoList(List.of(Photo.builder().build()))
                .commission(Commission.K10)
                .calculation(Calculation.CAPITAL)
                .balconyType(BalconyType.NO)
                .description("Description")
                .foundingDocument(FoundingDocument.TREATY)
                .communicationType(CommunicationType.SMS)
                .countRoom(CountRoom.K1)
                .totalArea(1)
                .address("г.Город, р.Район, вул.Вулиця,1")
                .heatingType(HeatingType.AUTONOMOUS)
                .kitchenArea(1)
                .layout(LayoutType.STUDIO)
                .price(1)
                .state(State.REPAIR)
                .type(TypeApartment.APARTMENT)
                .build();
        verify(apartmentRepo).saveAndFlush(apartmentSave);
    }
}