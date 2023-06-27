package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.entity.User;
import com.example.Swipe.Admin.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(BlackListController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class BlackListControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserServiceImpl userService;
    @Test
    void addToBlackList() throws Exception {
        when(userService.findById(1)).thenReturn(User.builder().build());
        mockMvc.perform(post("/add_to_black_list")
                .param("idUser","1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/users"));
    }

    @Test
    void deleteFromBlackList() throws Exception {
        when(userService.findById(1)).thenReturn(User.builder().build());
        mockMvc.perform(post("/delete_from_black_list")
                        .param("idUser","1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/black_list"));
    }
}