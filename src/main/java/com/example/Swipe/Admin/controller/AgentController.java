package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.service.AgentService;
import com.example.Swipe.Admin.service.ApartmentService;
import org.springframework.stereotype.Controller;

@Controller
public class AgentController {
    private final AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }
}
