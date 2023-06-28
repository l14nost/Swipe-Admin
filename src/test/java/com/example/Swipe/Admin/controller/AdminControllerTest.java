package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.dto.AdminDto;
import com.example.Swipe.Admin.service.impl.UserServiceImpl;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(AdminController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserServiceImpl userService;
    @MockBean
    private SecurityContext securityContext;


    @Test
    public void testUpdateProfile() throws Exception {
        AdminDto adminDto = AdminDto.builder()
                .mail("admin@gmail.com")
                .password("pass")
                .confirmPassword("pass")
                .idUser(1)
                .build();

        given(userService.uniqueMail(anyString(), any(BindingResult.class), anyInt(), anyString())).willReturn(new BeanPropertyBindingResult(adminDto, "user"));
        when(userService.getCurrentUser()).thenReturn(AdminDto.builder().idUser(1).password(new BCryptPasswordEncoder().encode("pass1")).mail("admin1@gmail.com").build());

        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn("mail@gmail.com");

        mockMvc.perform(post("/profile")
                        .flashAttr("user", adminDto))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/logout"));
    }
    @Test
    public void testValidUpdateProfileNotConfirmPassword() throws Exception {
        AdminDto adminDto = AdminDto.builder()
                .mail("admin@gmail.com")
                .password("pass")
                .confirmPassword("pass1")
                .idUser(1)
                .build();
        BindingResult result = new BeanPropertyBindingResult(adminDto,"user");
        result.addError(new FieldError("user", "mail", "Email already exists"));
        given(userService.uniqueMail(anyString(), any(BindingResult.class), anyInt(), anyString())).willReturn(result);
        when(userService.getCurrentUser()).thenReturn(AdminDto.builder().idUser(1).password(new BCryptPasswordEncoder().encode("pass")).mail("admin@gmail.com").build());
        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn("mail@gmail.com");
        mockMvc.perform(post("/profile")
                        .flashAttr("user", adminDto))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/admin_profile"));
    }

    @Test
    public void testValidUpdateProfile() throws Exception {
        AdminDto adminDto = AdminDto.builder()
                .mail("admin@gmail.com")
                .password("pass")
                .confirmPassword("pass")
                .idUser(1)
                .build();
        BindingResult result = new BeanPropertyBindingResult(adminDto,"user");
        result.addError(new FieldError("user", "mail", "Email already exists"));
        given(userService.uniqueMail(anyString(), any(BindingResult.class), anyInt(), anyString())).willReturn(result);
        when(userService.getCurrentUser()).thenReturn(AdminDto.builder().idUser(1).password(new BCryptPasswordEncoder().encode("pass")).mail("admin@gmail.com").build());
        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn("mail@gmail.com");
        mockMvc.perform(post("/profile")
                        .flashAttr("user", adminDto))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/admin_profile"));
    }
}