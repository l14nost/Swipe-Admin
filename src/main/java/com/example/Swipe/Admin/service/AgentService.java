package com.example.Swipe.Admin.service;

import com.example.Swipe.Admin.repository.AgentRepo;
import org.springframework.stereotype.Service;

@Service
public class AgentService {
    private final AgentRepo agentRepo;


    public AgentService(AgentRepo agentRepo) {
        this.agentRepo = agentRepo;
    }
}
