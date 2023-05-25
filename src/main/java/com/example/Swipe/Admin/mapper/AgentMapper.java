package com.example.Swipe.Admin.mapper;

import com.example.Swipe.Admin.dto.AgentDTO;
import com.example.Swipe.Admin.dto.ClientDTO;
import com.example.Swipe.Admin.entity.Agent;
import com.example.Swipe.Admin.entity.User;
import com.example.Swipe.Admin.enums.Role;
import com.example.Swipe.Admin.enums.TypeUser;
import com.example.Swipe.Admin.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class AgentMapper implements Function<Agent, AgentDTO> {
    public Agent toEntity(AgentDTO agentDTO){
//        List<User> users = new ArrayList<>();
//        users.add(userService.findById(agentDTO.getIdUser()));
        Agent agent = Agent.builder()
                .name(agentDTO.getName())
                .number(agentDTO.getNumber())
                .mail(agentDTO.getMail())
                .surname(agentDTO.getSurname())
//                .users(users)
                .type(agentDTO.getType())
                .build();
        if (agentDTO.getIdAgent()!=0){
            agent.setIdAgent(agentDTO.getIdAgent());
        }
        return agent;

    }

    @Override
    public AgentDTO apply(Agent agent) {
        return AgentDTO.builder()
                .idAgent(agent.getIdAgent())
                .type(agent.getType())
                .number(agent.getNumber())
                .mail(agent.getMail())
                .surname(agent.getSurname())
                .name(agent.getName())
                .build();

    }
}
