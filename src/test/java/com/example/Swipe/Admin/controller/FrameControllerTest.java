package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.dto.ApartmentDTO;
import com.example.Swipe.Admin.entity.Apartment;
import com.example.Swipe.Admin.entity.Frame;
import com.example.Swipe.Admin.entity.LCD;
import com.example.Swipe.Admin.service.impl.ApartmentServiceImpl;
import com.example.Swipe.Admin.service.impl.FrameServiceImpl;
import com.example.Swipe.Admin.service.impl.LCDServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(FrameController.class)
@AutoConfigureMockMvc(addFilters = false)
class FrameControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LCDServiceImpl lcdService;
    @MockBean
    private FrameServiceImpl frameService;
    @MockBean
    private ApartmentServiceImpl apartmentService;
    @MockBean
    private SecurityContext securityContext;

    @Test
    void updateFrame() throws Exception {
        when(frameService.findById(1)).thenReturn(Frame.builder().idFrame(1).lcd(LCD.builder().idLcd(1).build()).build());
        mockMvc.perform(post("/frame_update")
                .param("idFrame","1")
                .param("number","1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/lcd_edit/1"));
    }

    @Test
    void editFrame() throws Exception {
        Frame frame = Frame.builder().idFrame(1).lcd(LCD.builder().idLcd(1).build()).build();
        when(frameService.findById(1)).thenReturn(frame);
        List<ApartmentDTO> apartmentDTOS = List.of(
                ApartmentDTO.builder().build(),
                ApartmentDTO.builder().build(),
                ApartmentDTO.builder().build(),
                ApartmentDTO.builder().build(),
                ApartmentDTO.builder().build()
        );
        Pageable pageable = PageRequest.of(0,3);
        when(apartmentService.findAllForFramePagination(frame, pageable,0,"idApartment",1)).thenReturn(new PageImpl<>(apartmentDTOS));
        when(apartmentService.count(frame)).thenReturn(10);
        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        mockMvc.perform(get("/edit_frame/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/frame_edit"));
    }

    @Test
    void deleteFrameById() throws Exception {
        mockMvc.perform(post("/delete_frame")
                        .param("idFrame","1")
                        .param("idLcd","1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/lcd_edit/1"));
    }


    @Test
    void addFrame() throws Exception {
        when(lcdService.findById(1)).thenReturn(LCD.builder().frames(List.of()).build());
        mockMvc.perform(get("/add_frame/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/lcd_edit/1"));
    }

    @Test
    void addFrameToLcdWithFrames() throws Exception {
        when(lcdService.findById(1)).thenReturn(LCD.builder().frames(List.of(Frame.builder().num(1).build())).build());
        mockMvc.perform(get("/add_frame/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/lcd_edit/1"));
    }
}