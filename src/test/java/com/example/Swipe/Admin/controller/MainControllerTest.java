package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.entity.User;
import com.example.Swipe.Admin.enums.TypeUser;
import com.example.Swipe.Admin.service.impl.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

@WebMvcTest(MainController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class MainControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserServiceImpl userService;
    @MockBean
    private NewsServiceImpl  newsService;
    @MockBean
    private UserAddInfoServiceImpl userAddInfoService;
    @MockBean
    private LCDServiceImpl lcdService;
    @MockBean
    private ApartmentServiceImpl apartmentService;
    @MockBean
    private SecurityContext securityContext;

    @Test
    void main() throws Exception {
        when(userService.findAllByType(TypeUser.CLIENT)).thenReturn(
                List.of(
                        User.builder().build(),
                        User.builder().build(),
                        User.builder().build(),
                        User.builder().build()
                ));
        when(userService.findAllByType(TypeUser.CONTRACTOR)).thenReturn(
                List.of(
                        User.builder().build(),
                        User.builder().build(),
                        User.builder().build(),
                        User.builder().build()
                ));
        when(userService.findAllByType(TypeUser.NOTARY)).thenReturn(
                List.of(
                        User.builder().build(),
                        User.builder().build(),
                        User.builder().build(),
                        User.builder().build()
                ));
        when(apartmentService.count()).thenReturn(10);
        when(lcdService.count()).thenReturn(10);
        when(apartmentService.countNotNull()).thenReturn(10);


        for (int i = 1; i<13;i++){
            when(newsService.countNews(i)).thenReturn(1);
        }

        for (int i = 1; i<13;i++){
            when(userAddInfoService.countSub(i)).thenReturn(1);
        }
        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        mockMvc.perform(get("/statistics"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/admin_main"));


    }
}