package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.dto.AgentDTO;
import com.example.Swipe.Admin.dto.ClientDTO;
import com.example.Swipe.Admin.dto.UserAddInfoDTO;
import com.example.Swipe.Admin.enums.TypeNotification;
import com.example.Swipe.Admin.enums.TypeUser;
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
import org.springframework.validation.FieldError;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserServiceImpl userService;

    @MockBean
    private SecurityContext securityContext;

    @Test
    void userAdd() throws Exception {
        ClientDTO  clientDTO = ClientDTO.builder()
                .name("Name")
                .surname("Surname")
                .number("101123123")
                .type(TypeUser.CLIENT)
                .mail("user@gmail.com")
                .userAddInfoDTO(UserAddInfoDTO.builder().dateSub(LocalDate.now()).callSms(true).typeNotification(TypeNotification.ME).build())
                .build();
        BindingResult result = new BeanPropertyBindingResult( clientDTO,"user");
        given(userService.uniqueMail(anyString(), any(BindingResult.class), anyInt(), anyString())).willReturn(result);
        mockMvc.perform(post("/add_user")
                        .flashAttr("user",clientDTO)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/users"));
    }

    @Test
    void userAddValidClient() throws Exception {
        ClientDTO  clientDTO = ClientDTO.builder()
                .name("Name")
                .surname("Surname")
                .number("101123123")
                .type(TypeUser.CLIENT)
                .mail("user@gmail.com")
                .agent(AgentDTO.builder().build())
                .photo("qwe")
                .userAddInfoDTO(UserAddInfoDTO.builder().dateSub(LocalDate.now()).callSms(true).typeNotification(TypeNotification.ME).build())
                .build();
        when(userService.findByIdDTO(1)).thenReturn(clientDTO);
        BindingResult result = new BeanPropertyBindingResult( clientDTO,"user");
        result.addError(new FieldError("user", "mail", "Email already exists"));
        given(userService.uniqueMail(anyString(), any(BindingResult.class), anyInt(), anyString())).willReturn(result);

        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn("mail@gmail.com");

        mockMvc.perform(post("/add_user")
                        .flashAttr("user",clientDTO)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/user_add"));
    }
    @Test
    void userAddValidContractor() throws Exception {
        ClientDTO  clientDTO = ClientDTO.builder()
                .name("Name")
                .surname("Surname")
                .number("101123123")
                .type(TypeUser.CONTRACTOR)
                .mail("user@gmail.com")
                .agent(AgentDTO.builder().build())
                .photo("qwe")
                .userAddInfoDTO(UserAddInfoDTO.builder().dateSub(LocalDate.now()).callSms(true).typeNotification(TypeNotification.ME).build())
                .build();
        when(userService.findByIdDTO(1)).thenReturn(clientDTO);
        BindingResult result = new BeanPropertyBindingResult( clientDTO,"user");
        result.addError(new FieldError("user", "mail", "Email already exists"));
        given(userService.uniqueMail(anyString(), any(BindingResult.class), anyInt(), anyString())).willReturn(result);

        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn("mail@gmail.com");

        mockMvc.perform(post("/add_user")
                        .flashAttr("user",clientDTO)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/contractor_add"));
    }
    @Test
    void userAddValidNotary() throws Exception {
        ClientDTO  clientDTO = ClientDTO.builder()
                .name("Name")
                .surname("Surname")
                .number("101123123")
                .type(TypeUser.NOTARY)
                .mail("user@gmail.com")
                .agent(AgentDTO.builder().build())
                .photo("qwe")
                .userAddInfoDTO(UserAddInfoDTO.builder().dateSub(LocalDate.now()).callSms(true).typeNotification(TypeNotification.ME).build())
                .build();
        when(userService.findByIdDTO(1)).thenReturn(clientDTO);
        BindingResult result = new BeanPropertyBindingResult( clientDTO,"user");
        result.addError(new FieldError("user", "mail", "Email already exists"));
        given(userService.uniqueMail(anyString(), any(BindingResult.class), anyInt(), anyString())).willReturn(result);

        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn("mail@gmail.com");

        mockMvc.perform(post("/add_user")
                        .flashAttr("user",clientDTO)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/notary_add"));
    }

    @Test
    void userUpdate() throws Exception {
        ClientDTO  clientDTO = ClientDTO.builder()
                .name("Name")
                .surname("Surname")
                .number("101123123")
                .type(TypeUser.CLIENT)
                .mail("user@gmail.com")
                .userAddInfoDTO(UserAddInfoDTO.builder().dateSub(LocalDate.now()).callSms(true).typeNotification(TypeNotification.ME).build())
                .build();
        BindingResult result = new BeanPropertyBindingResult( clientDTO,"user");
        given(userService.uniqueMail(anyString(), any(BindingResult.class), anyInt(), anyString())).willReturn(result);
        mockMvc.perform(post("/user_edit/1")
                        .flashAttr("user",clientDTO)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/users"));
    }

    @Test
    void userUpdateValid() throws Exception {
        ClientDTO  clientDTO = ClientDTO.builder()
                .name("Name")
                .surname("Surname")
                .number("101123123")
                .type(TypeUser.CLIENT)
                .mail("user@gmail.com")
                .agent(AgentDTO.builder().build())
                .photo("qwe")
                .userAddInfoDTO(UserAddInfoDTO.builder().dateSub(LocalDate.now()).callSms(true).typeNotification(TypeNotification.ME).build())
                .build();
        when(userService.findByIdDTO(1)).thenReturn(clientDTO);
        BindingResult result = new BeanPropertyBindingResult( clientDTO,"user");
        result.addError(new FieldError("user", "mail", "Email already exists"));
        given(userService.uniqueMail(anyString(), any(BindingResult.class), anyInt(), anyString())).willReturn(result);

        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn("mail@gmail.com");

        mockMvc.perform(post("/user_edit/1")
                        .flashAttr("user",clientDTO)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/user_edit"));
    }

    @Test
    void deleteUser() throws Exception {
        mockMvc.perform(post("/delete_user/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/users"));
    }
}