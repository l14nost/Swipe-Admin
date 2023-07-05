package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.dto.ApartmentDTO;
import com.example.Swipe.Admin.dto.PhotoDTO;
import com.example.Swipe.Admin.entity.Apartment;
import com.example.Swipe.Admin.entity.Frame;
import com.example.Swipe.Admin.entity.LCD;
import com.example.Swipe.Admin.entity.User;
import com.example.Swipe.Admin.enums.*;
import com.example.Swipe.Admin.service.impl.ApartmentServiceImpl;
import com.example.Swipe.Admin.service.impl.LCDServiceImpl;
import com.example.Swipe.Admin.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(ApartmentController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class ApartmentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LCDServiceImpl lcdService;
    @MockBean
    private UserServiceImpl userService;
    @MockBean
    private ApartmentServiceImpl apartmentService;
    @MockBean
    private SecurityContext securityContext;


    @Test
    void deleteApartment() throws Exception {
        when(apartmentService.findById(1)).thenReturn(Apartment.builder().build());

        mockMvc.perform(post("/delete_apartment")
                .param("idApartment","1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/announcement"));
    }
    @Test
    void deleteApartmentFrame() throws Exception {
        when(apartmentService.findById(1)).thenReturn(Apartment.builder().frame(Frame.builder().idFrame(1).build()).build());

        mockMvc.perform(post("/delete_apartment")
                        .param("idApartment","1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/edit_frame/1"));
    }

    @Test
    void apartmentUpdate() throws Exception{

        ApartmentDTO apartmentDTO = ApartmentDTO.builder()
                .idApartment(1)
                .number(101)
                .description("1123123")
                .totalArea(100)
                .type(TypeApartment.APARTMENT)
                .layout(LayoutType.STUDIO)
                .state(State.REPAIR)
                .price(200020)
                .kitchenArea(15)
                .calculation(Calculation.CAPITAL)
                .foundingDocument(FoundingDocument.TREATY)
                .countRoom(CountRoom.K1)
                .communicationType(CommunicationType.SMS)
                .commission(Commission.K10)
                .address("г.Город, р.Район, ул.Улица,1")
                .balconyType(BalconyType.NO)
                .heatingType(HeatingType.INDIVIDUAL)
                .mainPhoto("123123")
                .build();
        when(apartmentService.findByIdDTO(1)).thenReturn( ApartmentDTO.builder().idApartment(1)
                .number(101)
                .description("1123123")
                .totalArea(100)
                .type(TypeApartment.APARTMENT)
                .layout(LayoutType.STUDIO)
                .state(State.REPAIR)
                .price(200020)
                .kitchenArea(20)
                .calculation(Calculation.CAPITAL)
                .foundingDocument(FoundingDocument.TREATY)
                .countRoom(CountRoom.K1)
                .communicationType(CommunicationType.SMS)
                .commission(Commission.K10)
                .address("г.Город, р.Район, ул.Улица,1")
                .balconyType(BalconyType.NO)
                .heatingType(HeatingType.INDIVIDUAL)
                .mainPhoto("123123")
                .photoList(List.of(PhotoDTO.builder().fileName("asdasd").build()))
                .build());

//        Authentication authentication = mock(Authentication.class);
//        when(securityContext.getAuthentication()).thenReturn(authentication);
//        SecurityContextHolder.setContext(securityContext);
//        when(authentication.getName()).thenReturn("mail@gmail.com");

        BindingResult result = new BeanPropertyBindingResult(apartmentDTO, "apartment");

        mockMvc.perform(post("/apartment_edit/1")
                .flashAttr("apartment",apartmentDTO)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .flashAttr("result",result))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/announcement"));

    }
    @Test
    void apartmentUpdateFrame() throws Exception{

        ApartmentDTO apartmentDTO = ApartmentDTO.builder()
                .idApartment(1)
                .number(101)
                .description("1123123")
                .totalArea(100)
                .type(TypeApartment.APARTMENT)
                .layout(LayoutType.STUDIO)
                .state(State.REPAIR)
                .price(200020)
                .kitchenArea(15)
                .calculation(Calculation.CAPITAL)
                .foundingDocument(FoundingDocument.TREATY)
                .countRoom(CountRoom.K1)
                .communicationType(CommunicationType.SMS)
                .commission(Commission.K10)
                .address("г.Город, р.Район, ул.Улица,1")
                .balconyType(BalconyType.NO)
                .heatingType(HeatingType.INDIVIDUAL)
                .mainPhoto("123123")
                .frame(1)
                .build();
        when(apartmentService.findByIdDTO(1)).thenReturn( ApartmentDTO.builder().idApartment(1)
                .number(101)
                .description("1123123")
                .totalArea(100)
                .type(TypeApartment.APARTMENT)
                .layout(LayoutType.STUDIO)
                .state(State.REPAIR)
                .price(200020)
                .kitchenArea(20)
                .calculation(Calculation.CAPITAL)
                .foundingDocument(FoundingDocument.TREATY)
                .countRoom(CountRoom.K1)
                .communicationType(CommunicationType.SMS)
                .commission(Commission.K10)
                .address("г.Город, р.Район, ул.Улица,1")
                .balconyType(BalconyType.NO)
                .heatingType(HeatingType.INDIVIDUAL)
                .mainPhoto("123123")
                        .frame(1)
                .photoList(List.of(PhotoDTO.builder().fileName("asdasd").build()))
                .build());

//        Authentication authentication = mock(Authentication.class);
//        when(securityContext.getAuthentication()).thenReturn(authentication);
//        SecurityContextHolder.setContext(securityContext);
//        when(authentication.getName()).thenReturn("mail@gmail.com");

        BindingResult result = new BeanPropertyBindingResult(apartmentDTO, "apartment");

        mockMvc.perform(post("/apartment_edit/1")
                        .flashAttr("apartment",apartmentDTO)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .flashAttr("result",result))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/edit_frame/1"));
    }

    @Test
    void apartmentUpdateValid() throws Exception{

        ApartmentDTO apartmentDTO = ApartmentDTO.builder()
                .idApartment(1)
                .number(101)
                .description("11")
                .totalArea(100)
                .type(TypeApartment.APARTMENT)
                .layout(LayoutType.STUDIO)
                .state(State.REPAIR)
                .price(200020)
                .kitchenArea(15)
                .calculation(Calculation.CAPITAL)
                .foundingDocument(FoundingDocument.TREATY)
                .countRoom(CountRoom.K1)
                .communicationType(CommunicationType.SMS)
                .commission(Commission.K10)
                .address("г.Город, р.Район, ул.Улица,1")
                .balconyType(BalconyType.NO)
                .heatingType(HeatingType.INDIVIDUAL)
                .mainPhoto("123123")
                .build();
        when(apartmentService.findByIdDTO(1)).thenReturn( ApartmentDTO.builder().idApartment(1)
                .number(101)
                .description("11")
                .totalArea(100)
                .type(TypeApartment.APARTMENT)
                .layout(LayoutType.STUDIO)
                .state(State.REPAIR)
                .price(200020)
                .kitchenArea(20)
                .calculation(Calculation.CAPITAL)
                .foundingDocument(FoundingDocument.TREATY)
                .countRoom(CountRoom.K1)
                .communicationType(CommunicationType.SMS)
                .commission(Commission.K10)
                .address("г.Город, р.Район, ул.Улица,1")
                .balconyType(BalconyType.NO)
                .heatingType(HeatingType.INDIVIDUAL)
                .mainPhoto("123123")
                .photoList(List.of(PhotoDTO.builder().fileName("asdasd").build()))
                .build());

        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn("mail@gmail.com");

        BindingResult result = new BeanPropertyBindingResult(apartmentDTO, "apartment");

        mockMvc.perform(post("/apartment_edit/1")
                        .flashAttr("apartment",apartmentDTO)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .flashAttr("result",result))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/apartment_edit"));

    }

    @Test
    void apartmentAdd() throws Exception {
        ApartmentDTO apartmentDTO = ApartmentDTO.builder()
                .idApartment(1)
                .number(101)
                .description("11123")
                .totalArea(100)
                .type(TypeApartment.APARTMENT)
                .layout(LayoutType.STUDIO)
                .state(State.REPAIR)
                .price(200020)
                .kitchenArea(15)
                .calculation(Calculation.CAPITAL)
                .foundingDocument(FoundingDocument.TREATY)
                .countRoom(CountRoom.K1)
                .communicationType(CommunicationType.SMS)
                .commission(Commission.K10)
                .address("г.Город, р.Район, ул.Улица,1")
                .balconyType(BalconyType.NO)
                .heatingType(HeatingType.INDIVIDUAL)
                .mainPhoto("123123")
                .build();
        BindingResult result = new BeanPropertyBindingResult(apartmentDTO, "apartment");

        mockMvc.perform(post("/add_apartment")
                        .flashAttr("apartment",apartmentDTO)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .flashAttr("result",result))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/announcement"));
    }

    @Test
    void apartmentAddValid() throws Exception {
        ApartmentDTO apartmentDTO = ApartmentDTO.builder()
                .idApartment(1)
                .number(101)
                .description("111")
                .totalArea(100)
                .type(TypeApartment.APARTMENT)
                .layout(LayoutType.STUDIO)
                .state(State.REPAIR)
                .price(200020)
                .kitchenArea(15)
                .calculation(Calculation.CAPITAL)
                .foundingDocument(FoundingDocument.TREATY)
                .countRoom(CountRoom.K1)
                .communicationType(CommunicationType.SMS)
                .commission(Commission.K10)
                .address("г.Город, р.Район, ул.Улица,1")
                .balconyType(BalconyType.NO)
                .heatingType(HeatingType.INDIVIDUAL)
                .mainPhoto("123123")
                .build();
        BindingResult result = new BeanPropertyBindingResult(apartmentDTO, "apartment");

        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn("mail@gmail.com");

        mockMvc.perform(post("/add_apartment")
                        .flashAttr("apartment",apartmentDTO)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .flashAttr("result",result))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/apartment_add"));
    }

    @Test
    void apartmentAddForFrame() throws Exception {
        ApartmentDTO apartmentDTO = ApartmentDTO.builder()
                .idApartment(1)
                .number(101)
                .description("11123")
                .totalArea(100)
                .type(TypeApartment.APARTMENT)
                .layout(LayoutType.STUDIO)
                .state(State.REPAIR)
                .price(200020)
                .kitchenArea(15)
                .calculation(Calculation.CAPITAL)
                .foundingDocument(FoundingDocument.TREATY)
                .countRoom(CountRoom.K1)
                .communicationType(CommunicationType.SMS)
                .commission(Commission.K10)
                .address("г.Город, р.Район, ул.Улица,1")
                .balconyType(BalconyType.NO)
                .heatingType(HeatingType.INDIVIDUAL)
                .mainPhoto("123123")
                .frame(1)
                .build();
        BindingResult result = new BeanPropertyBindingResult(apartmentDTO, "apartment");

        mockMvc.perform(post("/add_apartment/1")
                        .flashAttr("apartment",apartmentDTO)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .flashAttr("result",result))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/edit_frame/1"));
    }

    @Test
    void apartmentAddForFrameValid() throws Exception {
        ApartmentDTO apartmentDTO = ApartmentDTO.builder()
                .idApartment(1)
                .number(101)
                .description("11")
                .totalArea(100)
                .type(TypeApartment.APARTMENT)
                .layout(LayoutType.STUDIO)
                .state(State.REPAIR)
                .price(200020)
                .kitchenArea(15)
                .calculation(Calculation.CAPITAL)
                .foundingDocument(FoundingDocument.TREATY)
                .countRoom(CountRoom.K1)
                .communicationType(CommunicationType.SMS)
                .commission(Commission.K10)
                .address("г.Город, р.Район, ул.Улица,1")
                .balconyType(BalconyType.NO)
                .heatingType(HeatingType.INDIVIDUAL)
                .mainPhoto("123123")
                .frame(1)
                .build();
        BindingResult result = new BeanPropertyBindingResult(apartmentDTO, "apartment");

        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn("mail@gmail.com");

        mockMvc.perform(post("/add_apartment/1")
                        .flashAttr("apartment",apartmentDTO)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .flashAttr("result",result))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/apartment_frame"));
    }



    @Test
    void editPage() throws Exception {
        ApartmentDTO apartmentDTO = ApartmentDTO.builder()
                .idApartment(1)
                .number(101)
                .description("111")
                .totalArea(100)
                .type(TypeApartment.APARTMENT)
                .layout(LayoutType.STUDIO)
                .state(State.REPAIR)
                .price(200020)
                .kitchenArea(15)
                .calculation(Calculation.CAPITAL)
                .foundingDocument(FoundingDocument.TREATY)
                .countRoom(CountRoom.K1)
                .communicationType(CommunicationType.SMS)
                .commission(Commission.K10)
                .address("г.Город, р.Район, ул.Улица,1")
                .balconyType(BalconyType.NO)
                .heatingType(HeatingType.INDIVIDUAL)
                .mainPhoto("123123")
                .build();
        when(apartmentService.findByIdDTO(1)).thenReturn(apartmentDTO);
        when(lcdService.findAll()).thenReturn(List.of(LCD.builder().build()));
        when(userService.findAllByType(TypeUser.CLIENT)).thenReturn(List.of(User.builder().build()));
        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn("mail@gmail.com");

        mockMvc.perform(get("/apartment_edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/apartment_edit"));
    }



    @Test
    void addPage() throws Exception {

        when(lcdService.findAll()).thenReturn(List.of(LCD.builder().build()));
        when(userService.findAllByType(TypeUser.CLIENT)).thenReturn(List.of(User.builder().build()));
        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn("mail@gmail.com");

        mockMvc.perform(get("/add_apartment"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/apartment_add"));
    }

    @Test
    void addToFramePage() throws Exception {

         Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn("mail@gmail.com");

        mockMvc.perform(get("/add_apartment/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/apartment_frame"));
    }
}