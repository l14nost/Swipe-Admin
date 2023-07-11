package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.dto.ApartmentDTO;
import com.example.Swipe.Admin.dto.LcdDTO;
import com.example.Swipe.Admin.service.impl.ApartmentServiceImpl;
import com.example.Swipe.Admin.service.impl.LCDServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(MainHousesPageController.class)
@AutoConfigureMockMvc(addFilters = false)
class MainHousesPageControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ApartmentServiceImpl apartmentService;
    @MockBean
    private LCDServiceImpl lcdService;
    @MockBean
    private SecurityContext securityContext;

    @Test
    void housesMain() throws Exception {
        List<ApartmentDTO> apartmentDTOS = List.of(ApartmentDTO.builder().build());
        List<LcdDTO> lcdDTOs = List.of(LcdDTO.builder().build());
        Pageable pageable = PageRequest.of(0,10);
        when(lcdService.findAllPagination(pageable,"null","idLcd",1)).thenReturn(new PageImpl<>(lcdDTOs));
        when(apartmentService.findAllByFramePagination(pageable,0,"idApartment",1)).thenReturn(new PageImpl<>(apartmentDTOS));

        when(lcdService.count()).thenReturn(10);
        when(apartmentService.count()).thenReturn(10);


        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        mockMvc.perform(get("/lcds"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/houses_main"));
    }

    @Test
    void apartmentMain() throws Exception {
        List<ApartmentDTO> apartmentDTOS = List.of(ApartmentDTO.builder().build());
        List<LcdDTO> lcdDTOs = List.of(LcdDTO.builder().build());
        Pageable pageable = PageRequest.of(0,10);
        when(lcdService.findAllPagination(pageable,"null","idLcd",1)).thenReturn(new PageImpl<>(lcdDTOs));
        when(apartmentService.findAllByFramePagination(pageable,0,"idApartment",1)).thenReturn(new PageImpl<>(apartmentDTOS));

        when(lcdService.count()).thenReturn(10);
        when(apartmentService.count()).thenReturn(10);


        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        mockMvc.perform(get("/apartments"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/apartment_main"));
    }
}