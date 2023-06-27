package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.service.impl.PhotosServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(PhotosController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class PhotosControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PhotosServiceImpl photosService;

    @Test
    void deletePhoto() throws Exception {
        mockMvc.perform(post("/delete_photo_apartment")
                .param("idPhoto","1")
                .param("idApartment","1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/apartment_edit/1"));
    }

    @Test
    void deletePhotoLcd() throws Exception {
        mockMvc.perform(post("/delete_photo_lcd")
                        .param("idPhoto","1")
                        .param("idLcd","1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/lcd_edit/1"));
    }
}