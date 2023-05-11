package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.entity.Agent;
import com.example.Swipe.Admin.entity.User;
import com.example.Swipe.Admin.enums.TypeAgent;
import com.example.Swipe.Admin.enums.TypeUser;
import com.example.Swipe.Admin.service.impl.AgentServiceImpl;
import com.example.Swipe.Admin.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AgentController {
    private final UserServiceImpl userService;
    private final AgentServiceImpl agentServiceImpl;


    @GetMapping("/agent_add/{idUser}")
    public String addAgent(@PathVariable int idUser, Model model){
        model.addAttribute("idUser", idUser);
        return "admin/agent_add";
    }

    @PostMapping("/agent_add")
    public String saveAgent(@RequestParam int idUser,@RequestParam String name, @RequestParam String surname, @RequestParam String mail,@RequestParam String number, Model model){
        Agent agent = Agent.builder().number(number).name(name).surname(surname).mail(mail).build();
        User user = userService.findById(idUser);
        List<User> users = new ArrayList<>();
        users.add(user);
        agent.setUsers(users);
        if (user.getTypeUser() == TypeUser.CLIENT){
            agent.setType(TypeAgent.AGENT);
        }
        else if(user.getTypeUser() == TypeUser.CONTRACTOR){
            agent.setType(TypeAgent.SALES);
        }
        agentServiceImpl.saveEntity(agent);
        user.setAgent(agent);
        userService.updateEntity(user,idUser);
        return "redirect:/user_edit/"+idUser;
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
