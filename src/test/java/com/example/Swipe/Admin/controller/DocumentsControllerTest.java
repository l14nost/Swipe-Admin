package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.service.impl.DocumentsServiceImpl;
import com.example.Swipe.Admin.service.impl.FrameServiceImpl;
import com.example.Swipe.Admin.service.impl.LCDServiceImpl;
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

@WebMvcTest(DocumentsController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class DocumentsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DocumentsServiceImpl documentsService;

    @MockBean
    private FrameServiceImpl frameService;
    @MockBean
    private LCDServiceImpl lcdService;

    @Test
    void deleteDocument() throws Exception {
        mockMvc.perform(post("/delete_document")
                .param("idDocument","1")
                .param("idLcd","1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/lcd_edit/1"));
    }
}