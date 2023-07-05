package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.dto.AgentDTO;
import com.example.Swipe.Admin.entity.Agent;
import com.example.Swipe.Admin.entity.User;
import com.example.Swipe.Admin.enums.TypeUser;
import com.example.Swipe.Admin.service.impl.AgentServiceImpl;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(AgentController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class AgentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserServiceImpl userService;
    @MockBean
    private SecurityContext securityContext;
    @MockBean
    private AgentServiceImpl agentService;

    @Test
    void addPage() throws Exception {
        when(userService.findById(1)).thenReturn(User.builder().typeUser(TypeUser.CLIENT).build());


        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn("mail@gmail.com");

        mockMvc.perform(get("/agent_add/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/agent_add"));
    }

    @Test
    void editPage() throws Exception {
        when(agentService.findById(1)).thenReturn(Agent.builder().users(List.of(User.builder().typeUser(TypeUser.CONTRACTOR).build())).build());


        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn("mail@gmail.com");

        mockMvc.perform(get("/agent_edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/agent_edit"));
    }

    @Test
    void saveAgent() throws Exception {
        AgentDTO agentDTO = AgentDTO.builder()
                .idAgent(1)
                .number("630449985")
                .name("Name")
                .surname("Surname")
                .mail("mail@gmail.com")
                .idUser(1)
                .build();

        User user = User.builder().typeUser(TypeUser.CLIENT).build();
        when(userService.findById(1)).thenReturn(user);

        given(agentService.uniqueEmail(anyString(), any(BindingResult.class), anyInt(), anyString()))
                .will(invocation -> {
                    BindingResult bindingResult = invocation.getArgument(1);
                    return bindingResult;
                });

        mockMvc.perform(post("/agent_add/1")
                        .flashAttr("agent", agentDTO)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/user_edit/1"));
    }

    @Test
    void saveAgentContractor() throws Exception {
        AgentDTO agentDTO = AgentDTO.builder()
                .idAgent(1)
                .number("630449985")
                .name("Name")
                .surname("Surname")
                .mail("mail@gmail.com")
                .idUser(1)
                .build();

        User user = User.builder().typeUser(TypeUser.CONTRACTOR).build();
        when(userService.findById(1)).thenReturn(user);

        given(agentService.uniqueEmail(anyString(), any(BindingResult.class), anyInt(), anyString()))
                .will(invocation -> {
                    BindingResult bindingResult = invocation.getArgument(1);
                    return bindingResult;
                });

        mockMvc.perform(post("/agent_add/1")
                        .flashAttr("agent", agentDTO)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/user_edit/1"));


    }


    @Test
    void saveAgent_hasErrors() throws Exception {
        AgentDTO agentDTO = AgentDTO.builder()
                .idAgent(1)
                .number("630449985")
                .name("Name1")
                .surname("Surname")
                .mail("mail@gmail.com")
                .idUser(1)
                .build();
        when(userService.findById(1)).thenReturn(User.builder().typeUser(TypeUser.CLIENT).build());

        given(agentService.uniqueEmail(anyString(), any(BindingResult.class), anyInt(), anyString())).will(invocation -> {
            BindingResult bindingResult = invocation.getArgument(1);
            bindingResult.addError(new FieldError("agent", "mail", "Email already exists"));
            return bindingResult;
        });

        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn("mail@gmail.com");

        mockMvc.perform(post("/agent_add/1")
                        .flashAttr("agent", agentDTO)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/agent_add"));
    }

    @Test
    void updateAgent() throws Exception {
        AgentDTO agentDTO = AgentDTO.builder()
                .idAgent(1)
                .number("630449985")
                .name("Name")
                .surname("Surname")
                .mail("mail@gmail.com")
                .idUser(1)
                .build();

        User user = User.builder().typeUser(TypeUser.CLIENT).build();
        when(userService.findById(1)).thenReturn(user);

        given(agentService.uniqueEmail(anyString(), any(BindingResult.class), anyInt(), anyString()))
                .will(invocation -> {
                    BindingResult bindingResult = invocation.getArgument(1);
                    return bindingResult;
                });

        mockMvc.perform(post("/agent_edit/1")
                        .flashAttr("agent", agentDTO)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/user_edit/1"));
    }

    @Test
    void updateAgentValid() throws Exception {
        AgentDTO agentDTO = AgentDTO.builder()
                .idAgent(1)
                .number("630449985")
                .name("Name")
                .surname("Surname")
                .mail("mail@gmail.com")
                .idUser(1)
                .build();

        User user = User.builder().typeUser(TypeUser.CLIENT).build();
        when(userService.findById(1)).thenReturn(user);

        given(agentService.uniqueEmail(anyString(), any(BindingResult.class), anyInt(), anyString()))
                .will(invocation -> {
                    BindingResult bindingResult = invocation.getArgument(1);
                    bindingResult.addError(new FieldError("agent", "mail", "Email already exists"));
                    return bindingResult;
                });
        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn("mail@gmail.com");

        mockMvc.perform(post("/agent_edit/1")
                        .flashAttr("agent", agentDTO)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/agent_edit"));
    }

}