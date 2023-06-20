package com.example.Swipe.Admin.service.impl;

import com.example.Swipe.Admin.dto.AgentDTO;
import com.example.Swipe.Admin.entity.Agent;
import com.example.Swipe.Admin.entity.User;
import com.example.Swipe.Admin.mapper.AgentMapper;
import com.example.Swipe.Admin.repository.AgentRepo;
import com.example.Swipe.Admin.service.AgentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AgentServiceImpl implements AgentService {
    private Logger log = LoggerFactory.getLogger(AgentServiceImpl.class);
    private final UserServiceImpl userService;
    private final AgentRepo agentRepo;


    public void saveDTO(AgentDTO agentDTO){
        Agent agent = AgentMapper.toEntity(agentDTO);
        User user = userService.findById(agentDTO.getIdUser());
        agentRepo.save(agent);
        user.setAgent(agent);
        userService.updateEntity(user, agentDTO.getIdUser());
    }
    public BindingResult uniqueEmail(String mail, BindingResult result, int id, String method){
        List<Agent> agents = agentRepo.findAllByMail(mail);
        if (agents.size()>=2){
            result.addError(new FieldError("agent", "mail", "Email already exists"));
            return result;
        }
        else if (method.equals("update")&&agents.size()==1){
            if (agents.get(0).getIdAgent()==id){
                return result;
            }
            result.addError(new FieldError("agent", "mail", "Email already exists"));
            return result;
        }
        else if (method.equals("add")&&agents.size()!=0){
            result.addError(new FieldError("agent", "mail", "Email already exists"));
            return result;
        }
        else return result;
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
            return null;
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
            agentRepo.saveAndFlush(agentUpdate);
        }
    }
    public void updateDTO(AgentDTO agentDTO, int id) {
        Agent agent = AgentMapper.toEntity(agentDTO);
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
            agentRepo.saveAndFlush(agentUpdate);
        }
    }
}
