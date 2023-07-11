package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.dto.ClientDTO;
import com.example.Swipe.Admin.enums.TypeUser;
import com.example.Swipe.Admin.service.impl.UserServiceImpl;
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
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(MainUserPageController.class)
@AutoConfigureMockMvc(addFilters = false)
class MainUserPageControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;
    @MockBean
    private SecurityContext securityContext;

    @Test
    void usersMain() throws Exception {
        List<ClientDTO> clientDTOS = List.of(
                ClientDTO.builder().build(),
                ClientDTO.builder().build(),
                ClientDTO.builder().build(),
                ClientDTO.builder().build()
        );
        Pageable pageable = PageRequest.of(0,10);
        when(userService.findAllByTypePagination(TypeUser.CLIENT,pageable,"null","idUser",1)).thenReturn(new PageImpl<ClientDTO>(clientDTOS));

        when(userService.findAllByTypePagination(TypeUser.CONTRACTOR,pageable,"null","idUser",1)).thenReturn(new PageImpl<ClientDTO>(clientDTOS));

        when(userService.findAllByTypePagination(TypeUser.NOTARY,pageable,"null","idUser",1)).thenReturn(new PageImpl<ClientDTO>(clientDTOS));


        when(userService.countByTypeUser(TypeUser.CLIENT)).thenReturn(10);
        when(userService.countByTypeUser(TypeUser.CONTRACTOR)).thenReturn(10);
        when(userService.countByTypeUser(TypeUser.NOTARY)).thenReturn(10);

        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/user_main"));
    }

    @Test
    void contractorsMain() throws Exception {
        List<ClientDTO> clientDTOS = List.of(
                ClientDTO.builder().build(),
                ClientDTO.builder().build(),
                ClientDTO.builder().build(),
                ClientDTO.builder().build()
        );
        Pageable pageable = PageRequest.of(0,10);
        when(userService.findAllByTypePagination(TypeUser.CLIENT,pageable,"null","idUser",1)).thenReturn(new PageImpl<ClientDTO>(clientDTOS));

        when(userService.findAllByTypePagination(TypeUser.CONTRACTOR,pageable,"null","idUser",1)).thenReturn(new PageImpl<ClientDTO>(clientDTOS));

        when(userService.findAllByTypePagination(TypeUser.NOTARY,pageable,"null","idUser",1)).thenReturn(new PageImpl<ClientDTO>(clientDTOS));


        when(userService.countByTypeUser(TypeUser.CLIENT)).thenReturn(10);
        when(userService.countByTypeUser(TypeUser.CONTRACTOR)).thenReturn(10);
        when(userService.countByTypeUser(TypeUser.NOTARY)).thenReturn(10);

        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        mockMvc.perform(get("/contractors"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/contractor_main"));
    }

    @Test
    void notaryMain() throws Exception {
        List<ClientDTO> clientDTOS = List.of(
                ClientDTO.builder().build(),
                ClientDTO.builder().build(),
                ClientDTO.builder().build(),
                ClientDTO.builder().build()
        );
        Pageable pageable = PageRequest.of(0,10);
        when(userService.findAllByTypePagination(TypeUser.CLIENT,pageable,"null","idUser",1)).thenReturn(new PageImpl<ClientDTO>(clientDTOS));

        when(userService.findAllByTypePagination(TypeUser.CONTRACTOR,pageable,"null","idUser",1)).thenReturn(new PageImpl<ClientDTO>(clientDTOS));

        when(userService.findAllByTypePagination(TypeUser.NOTARY,pageable,"null","idUser",1)).thenReturn(new PageImpl<ClientDTO>(clientDTOS));


        when(userService.countByTypeUser(TypeUser.CLIENT)).thenReturn(10);
        when(userService.countByTypeUser(TypeUser.CONTRACTOR)).thenReturn(10);
        when(userService.countByTypeUser(TypeUser.NOTARY)).thenReturn(10);

        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        mockMvc.perform(get("/notaries"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/notary_main"));
    }
}