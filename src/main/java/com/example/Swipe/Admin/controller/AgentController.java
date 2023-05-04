package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.entity.Agent;
import com.example.Swipe.Admin.service.impl.AgentServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AgentController {
    private final AgentServiceImpl agentServiceImpl;

    public AgentController(AgentServiceImpl agentServiceImpl) {
        this.agentServiceImpl = agentServiceImpl;
    }

    @GetMapping("/agent_edit/{id}")
    public String agentEdit(@PathVariable String id, Model model){
        String[] idList = id.split("_");
        model.addAttribute("agent",agentServiceImpl.findById(Integer.valueOf(idList[0])));
        model.addAttribute("idUser", Integer.valueOf(idList[1]));
        return "admin/agent_edit";
    }
    @PostMapping("/agent_update/{id}")
    public String agentUpdate(@PathVariable int id, @RequestParam String name,@RequestParam String surname,@RequestParam String number,@RequestParam String mail,@RequestParam(name = "idUser") int idUser, Model model){
        Agent agent = Agent.builder().surname(surname).name(name).number(number).mail(mail).build();
        agentServiceImpl.updateEntity(agent,id);
        return "redirect:/user_edit/"+idUser;
    }
}
