package com.example.Swipe.Admin.service.impl;

import com.example.Swipe.Admin.dto.AgentDTO;
import com.example.Swipe.Admin.entity.Agent;
import com.example.Swipe.Admin.entity.User;
import com.example.Swipe.Admin.enums.TypeAgent;
import com.example.Swipe.Admin.repository.AgentRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AgentServiceImplTest {
    @Mock
    private UserServiceImpl userService;
    @Mock
    private AgentRepo agentRepo;
    @InjectMocks
    private AgentServiceImpl agentService;
    @Test
    public void saveEntityTest(){
        Agent agent = Agent.builder()
                .type(TypeAgent.AGENT)
                .mail("newmail@gmail.com")
                .name("AgentTest")
                .surname("MockTest")
                .number("123123123")
                .build();
        agentService.saveEntity(agent);
        verify(agentRepo).save(agent);
    }
    @Test
    public void saveDTO(){
        AgentDTO agent = AgentDTO.builder()
                .type(TypeAgent.AGENT)
                .mail("newmail@gmail.com")
                .name("AgentTest")
                .surname("MockTest")
                .number("123123123")
                .idUser(1)
                .build();
        Agent agent1 = Agent.builder()
                .type(TypeAgent.AGENT)
                .mail("newmail@gmail.com")
                .name("AgentTest")
                .surname("MockTest")
                .number("123123123")
                .build();
        when(userService.findById(1)).thenReturn(User.builder().idUser(1).build());
        agentService.saveDTO(agent);
        verify(agentRepo).save(agent1);
    }
    @Test
    public void deleteById(){
        agentService.deleteById(1);
        verify(agentRepo).deleteById(1);
    }
    @Test
    public void findByIdAgentTest(){
        Agent agent = Agent.builder()
                .idAgent(8)
                .name("11")
                .surname("11")
                .mail("11")
                .number("11")
                .type(TypeAgent.AGENT).build();
        when(agentRepo.findById(8)).thenReturn(Optional.of(agent));


        Agent res = agentService.findById(8);

        assertEquals("11",res.getName());
        verify(agentRepo, times(1)).findById(8);
    }
    @Test
    public void findByIdAgentTestFailed(){
        when(agentRepo.findById(1)).thenReturn(Optional.empty());
        assertNull(agentService.findById(1));
    }
    @Test
    public void findAllTest(){
        List<Agent> agents = new ArrayList<>();
        for (int i = 0; i<28;i++){
            agents.add(new Agent());
        }
        when(agentRepo.findAll()).thenReturn(agents);

        List<Agent> agentList = agentService.findAll();
        assertEquals(28,agentList.size());
    }
    @Test
    public void updateEntity(){
        Agent agent = Agent.builder()
                .idAgent(1)
                .type(TypeAgent.AGENT)
                .mail("newmail@gmail.com")
                .name("AgentTest")
                .surname("MockTest")
                .number("123123123")
                .build();
        when(agentRepo.findById(1)).thenReturn(Optional.of(agent));
        Agent agentUpdate = Agent.builder()
                .type(TypeAgent.AGENT)
                .mail("new@gmail.com")
                .name("Test")
                .surname("MockTest")
                .number("12312312")
                .build();
        agentService.updateEntity(agentUpdate,1);
        Agent agentSave = Agent.builder()
                .idAgent(1)
                .type(TypeAgent.AGENT)
                .mail("new@gmail.com")
                .name("Test")
                .surname("MockTest")
                .number("12312312")
                .build();
        verify(agentRepo).saveAndFlush(agentSave);
    }
    @Test
    public void updateDTO(){
        Agent agent = Agent.builder()
                .idAgent(1)
                .type(TypeAgent.AGENT)
                .mail("newmail@gmail.com")
                .name("AgentTest")
                .surname("MockTest")
                .number("123123123")
                .build();
        when(agentRepo.findById(1)).thenReturn(Optional.of(agent));
        AgentDTO agentUpdate = AgentDTO.builder()
                .type(TypeAgent.AGENT)
                .mail("new@gmail.com")
                .name("Test")
                .surname("MockTest")
                .number("12312312")
                .build();
        agentService.updateDTO(agentUpdate,1);
        Agent agentSave = Agent.builder()
                .idAgent(1)
                .type(TypeAgent.AGENT)
                .mail("new@gmail.com")
                .name("Test")
                .surname("MockTest")
                .number("12312312")
                .build();
        verify(agentRepo).saveAndFlush(agentSave);
    }

    @Test
    void uniqueMailNull(){
        when(agentRepo.findAllByMail("mail@gmail.com")).thenReturn(List.of(Agent.builder().build(), Agent.builder().build()));
        BindingResult result = mock(BindingResult.class);
        BindingResult result1 = agentService.uniqueEmail("mail@gmail.com",result,0,"add");

        assertEquals(result1,result);
        verify(result, times(1)).addError(any(FieldError.class));
    }

    @Test
    void uniqueMailUpdate(){
        when(agentRepo.findAllByMail("mail@gmail.com")).thenReturn(List.of(Agent.builder().idAgent(1).build()));
        BindingResult result = mock(BindingResult.class);
        BindingResult result1 = agentService.uniqueEmail("mail@gmail.com",result,1,"update");

        assertEquals(result1,result);
        verify(result, never()).addError(any(FieldError.class));
    }

    @Test
    void uniqueMailUpdateError(){
        when(agentRepo.findAllByMail("mail@gmail.com")).thenReturn(List.of(Agent.builder().idAgent(1).build()));
        BindingResult result = mock(BindingResult.class);
        BindingResult result1 = agentService.uniqueEmail("mail@gmail.com",result,2,"update");
        assertEquals(result1,result);
        verify(result, times(1)).addError(any(FieldError.class));
    }

    @Test
    void uniqueMailAddError(){
        when(agentRepo.findAllByMail("mail@gmail.com")).thenReturn(List.of(Agent.builder().build()));
        BindingResult result = mock(BindingResult.class);
        BindingResult result1 = agentService.uniqueEmail("mail@gmail.com",result,2,"add");
        assertEquals(result1,result);
        verify(result, times(1)).addError(any(FieldError.class));
    }

    @Test
    void uniqueMailSuccess(){
        when(agentRepo.findAllByMail("mail@gmail.com")).thenReturn(List.of());
        BindingResult result = mock(BindingResult.class);
        BindingResult result1 = agentService.uniqueEmail("mail@gmail.com",result,1,"add");
        assertEquals(result1,result);
        verify(result, never()).addError(any(FieldError.class));
    }
}