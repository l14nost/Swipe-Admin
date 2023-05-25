package com.example.Swipe.Admin.service.impl;

import com.example.Swipe.Admin.dto.AgentDTO;
import com.example.Swipe.Admin.entity.Address;
import com.example.Swipe.Admin.entity.Agent;
import com.example.Swipe.Admin.entity.User;
import com.example.Swipe.Admin.mapper.AgentMapper;
import com.example.Swipe.Admin.repository.AgentRepo;
import com.example.Swipe.Admin.service.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AgentServiceImpl implements AgentService {
    private final UserServiceImpl userService;
    private final AgentRepo agentRepo;
    private final AgentMapper agentMapper;


    public void saveDTO(AgentDTO agentDTO){
        Agent agent = agentMapper.toEntity(agentDTO);
        User user = userService.findById(agentDTO.getIdUser());
        agentRepo.save(agent);
        user.setAgent(agent);
        userService.updateEntity(user, agentDTO.getIdUser());
    }

    @Override
    public List<Agent> findAll() {
        return agentRepo.findAll();
    }

    @Override
    public Agent findById(int id) {
        Optional<Agent> agent = agentRepo.findById(id);
        if(agent.isPresent()){
            return agent.get();
        }
        else {
            return Agent.builder().name("").surname("").mail("").number("").build();
        }
    }

    @Override
    public void saveEntity(Agent agent) {
        agentRepo.save(agent);
    }

    @Override
    public void deleteById(int id) {
        agentRepo.deleteById(id);
    }

    @Override
    public void updateEntity(Agent agent, int id) {
        Optional<Agent> agentOptional = agentRepo.findById(id);
        if(agentOptional.isPresent()){
            Agent agentUpdate = agentOptional.get();
            if(agent.getName()!=null){
                agentUpdate.setName(agent.getName());
            }
            if(agent.getMail()!=null){
                agentUpdate.setMail(agent.getMail());
            }
            if(agent.getNumber()!=null){
                agentUpdate.setNumber(agent.getNumber());
            }
            if(agent.getSurname()!=null){
                agentUpdate.setSurname(agent.getSurname());
            }
            if(agent.getUsers()!=null){
                agentUpdate.setUsers(agent.getUsers());
            }
            agentRepo.saveAndFlush(agentUpdate);
        }
        else {
            Agent agentUpdate = Agent.builder().name("").surname("").mail("").number("").build();
            agentRepo.saveAndFlush(agentUpdate);
        }
    }
    public void updateEntityDTO(AgentDTO agentDTO, int id) {
        Agent agent = agentMapper.toEntity(agentDTO);
        Optional<Agent> agentOptional = agentRepo.findById(id);
        if(agentOptional.isPresent()){
            Agent agentUpdate = agentOptional.get();
            if(agent.getName()!=null){
                agentUpdate.setName(agent.getName());
            }
            if(agent.getMail()!=null){
                agentUpdate.setMail(agent.getMail());
            }
            if(agent.getNumber()!=null){
                agentUpdate.setNumber(agent.getNumber());
            }
            if(agent.getSurname()!=null){
                agentUpdate.setSurname(agent.getSurname());
            }
            if(agent.getUsers()!=null){
                agentUpdate.setUsers(agent.getUsers());
            }
            agentRepo.saveAndFlush(agentUpdate);
        }
        else {
            Agent agentUpdate = Agent.builder().name("").surname("").mail("").number("").build();
            agentRepo.saveAndFlush(agentUpdate);
        }
    }
}
