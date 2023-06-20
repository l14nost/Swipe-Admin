package com.example.Swipe.Admin.mapper;

import com.example.Swipe.Admin.dto.AgentDTO;
import com.example.Swipe.Admin.dto.DocumentDTO;
import com.example.Swipe.Admin.dto.FrameDTO;
import com.example.Swipe.Admin.entity.Agent;
import com.example.Swipe.Admin.entity.Documents;
import com.example.Swipe.Admin.entity.Frame;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class FrameMapper  {
    private final ApartmentMapper apartmentMapper;
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

    public static FrameDTO apply(Frame frame) {
        return FrameDTO.builder()
                .idFrame(frame.getIdFrame())
                .num(frame.getNum())
                .apartmentList(frame.getApartmentList().stream().map(ApartmentMapper::apply).collect(Collectors.toList()))
                .build();

    }
}
