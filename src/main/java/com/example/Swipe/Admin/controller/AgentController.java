package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.service.impl.AgentServiceImpl;
import org.springframework.stereotype.Controller;

@Controller
public class AgentController {
    private final AgentServiceImpl agentServiceImpl;

    public AgentController(AgentServiceImpl agentServiceImpl) {
        this.agentServiceImpl = agentServiceImpl;
    }
}
