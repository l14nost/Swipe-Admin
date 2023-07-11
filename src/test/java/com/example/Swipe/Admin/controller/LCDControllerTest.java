package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.dto.ClientDTO;
import com.example.Swipe.Admin.dto.LcdDTO;
import com.example.Swipe.Admin.entity.User;
import com.example.Swipe.Admin.enums.*;
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

@ExtendWith(MockitoExtension.class)
@WebMvcTest(LCDController.class)
@AutoConfigureMockMvc(addFilters = false)
class LCDControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LCDServiceImpl lcdService;
    @MockBean
    private UserServiceImpl userService;

    @MockBean
    private SecurityContext securityContext;


    @Test
    void editLcdPage() throws Exception{
        LcdDTO lcdDTO = LcdDTO.builder()
                .idLcd(1)
                .name("Name")
                .description("Description1")
                .sumContract("Sum")
                .territory(TerritoryType.CLOSE)
                .technology(TechnologyType.PANEL)
                .gas(GasType.NO)
                .waterSupply(HeatingType.AUTONOMOUS)
                .lcdClass(ClassType.MASS)
                .type(LCDType.FIVE)
                .address("г.Город, р.Район, ул.Улица,1")
                .heating(HeatingType.AUTONOMOUS)
                .sewerage(HeatingType.CENTRAL)
                .appointment("Appointment")
                .height(4)
                .status(StatusLCDType.APARTMENT)
                .advantages("123")
                .communal(CommunalType.HALF)
                .formalization("Appointment")
                .distanceSea(12)
                .typePayment("Appointment")
                .mainPhoto("123123")
                .contractor(12).build();
        when(lcdService.findByIdDTO(1)).thenReturn(lcdDTO);
        when(userService.findAllByType(TypeUser.CONTRACTOR)).thenReturn(List.of(User.builder().build()));
        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn("mail@gmail.com");
        mockMvc.perform(get("/lcd_edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/lcd_edit"));
    }

    @Test
    void addLcdPage() throws Exception{
        when(userService.findAllByType(TypeUser.CONTRACTOR)).thenReturn(List.of(User.builder().build()));
        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn("mail@gmail.com");
        mockMvc.perform(get("/add_lcd"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/lcd_add"));
    }
    @Test
    void lcdUpdate() throws Exception {
        LcdDTO lcdDTO = LcdDTO.builder()
                .idLcd(1)
                .name("Name")
                .description("Description1")
                .sumContract("Sum")
                .territory(TerritoryType.CLOSE)
                .technology(TechnologyType.PANEL)
                .gas(GasType.NO)
                .waterSupply(HeatingType.AUTONOMOUS)
                .lcdClass(ClassType.MASS)
                .type(LCDType.FIVE)
                .address("г.Город, р.Район, ул.Улица,1")
                .heating(HeatingType.AUTONOMOUS)
                .sewerage(HeatingType.CENTRAL)
                .appointment("Appointment")
                .height(4)
                .status(StatusLCDType.APARTMENT)
                .advantages("123")
                .communal(CommunalType.HALF)
                .formalization("Appointment")
                .distanceSea(12)
                .typePayment("Appointment")
                .mainPhoto("123123")
                .contractor(12).build();

        BindingResult bindingResult = new BeanPropertyBindingResult(lcdDTO,"lcd");

        when(userService.findAllByType(TypeUser.CONTRACTOR)).thenReturn(List.of(User.builder().build()));

        when(lcdService.findByIdDTO(1)).thenReturn(lcdDTO);

        mockMvc.perform(post("/lcd_edit/1")
                .flashAttr("lcd",lcdDTO)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .flashAttr("bindingResult",bindingResult))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/lcds"));

    }

    @Test
    void lcdUpdateValid() throws Exception {
        LcdDTO lcdDTO = LcdDTO.builder()
                .idLcd(1)
                .name("Name")
                .description("Description1")
                .sumContract("Sum")
                .territory(TerritoryType.CLOSE)
                .technology(TechnologyType.PANEL)
                .gas(GasType.NO)
                .waterSupply(HeatingType.AUTONOMOUS)
                .lcdClass(ClassType.MASS)
                .type(LCDType.FIVE)
                .address("г.Город, р.Район, ул.Улица,1")
                .heating(HeatingType.AUTONOMOUS)
                .sewerage(HeatingType.CENTRAL)
                .appointment("Appointment")
                .height(2)
                .status(StatusLCDType.APARTMENT)
                .advantages("123")
                .communal(CommunalType.HALF)
                .formalization("Appointment")
                .distanceSea(12)
                .typePayment("Appointment")
                .mainPhoto("123123")
                .contractor(12).build();

        BindingResult bindingResult = new BeanPropertyBindingResult(lcdDTO,"lcd");

        when(userService.findAllByType(TypeUser.CONTRACTOR)).thenReturn(List.of(User.builder().build()));

        when(lcdService.findByIdDTO(1)).thenReturn(lcdDTO);

        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn("mail@gmail.com");

        mockMvc.perform(post("/lcd_edit/1")
                        .flashAttr("lcd",lcdDTO)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .flashAttr("bindingResult",bindingResult))
                .andExpect(status().isAccepted())
                .andExpect(view().name("admin/lcd_edit"));

    }
    @Test
    void lcdUpdateValidNull() throws Exception {
        LcdDTO lcdDTO = LcdDTO.builder()
                .idLcd(1)
                .name("Name")
                .description("Description1")
                .sumContract("Sum")
                .territory(TerritoryType.CLOSE)
                .technology(TechnologyType.PANEL)
                .gas(GasType.NO)
                .waterSupply(HeatingType.AUTONOMOUS)
                .lcdClass(ClassType.MASS)
                .type(LCDType.FIVE)
                .address("г.Город, р.Район, ул.Улица,1")
                .heating(HeatingType.AUTONOMOUS)
                .sewerage(HeatingType.CENTRAL)
                .appointment("Appointment")
                .height(2)
                .status(StatusLCDType.APARTMENT)
                .communal(CommunalType.HALF)
                .formalization("Appointment")
                .distanceSea(12)
                .typePayment("Appointment")
                .mainPhoto("123123")
                .contractor(12).build();

        BindingResult bindingResult = new BeanPropertyBindingResult(lcdDTO,"lcd");

        when(userService.findAllByType(TypeUser.CONTRACTOR)).thenReturn(List.of(User.builder().build()));

        when(lcdService.findByIdDTO(1)).thenReturn(lcdDTO);

        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn("mail@gmail.com");

        mockMvc.perform(post("/lcd_edit/1")
                        .flashAttr("lcd",lcdDTO)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .flashAttr("bindingResult",bindingResult))
                .andExpect(status().isAccepted())
                .andExpect(view().name("admin/lcd_edit"));

    }

    @Test
    void addLcd() throws Exception {
        LcdDTO lcdDTO = LcdDTO.builder()
                .idLcd(1)
                .name("Name")
                .description("Description1")
                .sumContract("Sum")
                .territory(TerritoryType.CLOSE)
                .technology(TechnologyType.PANEL)
                .gas(GasType.NO)
                .waterSupply(HeatingType.AUTONOMOUS)
                .lcdClass(ClassType.MASS)
                .type(LCDType.FIVE)
                .address("г.Город, р.Район, ул.Улица,1")
                .heating(HeatingType.AUTONOMOUS)
                .sewerage(HeatingType.CENTRAL)
                .appointment("Appointment")
                .height(4)
                .status(StatusLCDType.APARTMENT)
                .advantages("123")
                .communal(CommunalType.HALF)
                .formalization("Appointment")
                .distanceSea(12)
                .typePayment("Appointment")
                .mainPhoto("123123")
                .contractor(12).build();

        BindingResult bindingResult = new BeanPropertyBindingResult(lcdDTO,"lcd");


        mockMvc.perform(post("/add_lcd")
                        .flashAttr("lcd",lcdDTO)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .flashAttr("bindingResult",bindingResult))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/lcds"));
    }

    @Test
    void addLcdValid() throws Exception {
        LcdDTO lcdDTO = LcdDTO.builder()
                .idLcd(1)
                .name("Name")
                .description("Description1")
                .sumContract("Sum")
                .territory(TerritoryType.CLOSE)
                .technology(TechnologyType.PANEL)
                .gas(GasType.NO)
                .waterSupply(HeatingType.AUTONOMOUS)
                .lcdClass(ClassType.MASS)
                .type(LCDType.FIVE)
                .address("г.Город, р.Район, ул.Улица,1")
                .heating(HeatingType.AUTONOMOUS)
                .sewerage(HeatingType.CENTRAL)
                .appointment("Appointment")
                .height(2)
                .status(StatusLCDType.APARTMENT)
                .advantages("123")
                .communal(CommunalType.HALF)
                .formalization("Appointment")
                .distanceSea(12)
                .typePayment("Appointment")
                .mainPhoto("123123")
                .contractor(12).build();

        BindingResult bindingResult = new BeanPropertyBindingResult(lcdDTO,"lcd");

        when(userService.findAllByType(TypeUser.CONTRACTOR)).thenReturn(List.of(User.builder().build()));

        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn("mail@gmail.com");

        mockMvc.perform(post("/add_lcd")
                        .flashAttr("lcd",lcdDTO)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .flashAttr("bindingResult",bindingResult))
                .andExpect(status().isAccepted())
                .andExpect(view().name("admin/lcd_add"));
    }
    @Test
    void addLcdValid_Null() throws Exception {
        LcdDTO lcdDTO = LcdDTO.builder()
                .idLcd(1)
                .name("Name")
                .description("Description1")
                .sumContract("Sum")
                .territory(TerritoryType.CLOSE)
                .technology(TechnologyType.PANEL)
                .gas(GasType.NO)
                .waterSupply(HeatingType.AUTONOMOUS)
                .lcdClass(ClassType.MASS)
                .type(LCDType.FIVE)
                .address("г.Город, р.Район, ул.Улица,1")
                .heating(HeatingType.AUTONOMOUS)
                .sewerage(HeatingType.CENTRAL)
                .appointment("Appointment")
                .height(2)
                .status(StatusLCDType.APARTMENT)
//                .advantages("123")
                .communal(CommunalType.HALF)
                .formalization("Appointment")
                .distanceSea(12)
                .typePayment("Appointment")
                .mainPhoto("123123")
                .contractor(12).build();

        BindingResult bindingResult = new BeanPropertyBindingResult(lcdDTO,"lcd");

        when(userService.findAllByType(TypeUser.CONTRACTOR)).thenReturn(List.of(User.builder().build()));

        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn("mail@gmail.com");

        mockMvc.perform(post("/add_lcd")
                        .flashAttr("lcd",lcdDTO)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .flashAttr("bindingResult",bindingResult))
                .andExpect(status().isAccepted())
                .andExpect(view().name("admin/lcd_add"));
    }

    @Test
    void deleteLcd() throws Exception {
        mockMvc.perform(post("/delete_lcd")
                .param("idLcd","1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/lcds"));
    }
}