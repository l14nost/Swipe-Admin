package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.dto.AgentDTO;
import com.example.Swipe.Admin.entity.Agent;
import com.example.Swipe.Admin.entity.User;
import com.example.Swipe.Admin.enums.TypeAgent;
import com.example.Swipe.Admin.enums.TypeUser;
import com.example.Swipe.Admin.service.impl.AgentServiceImpl;
import com.example.Swipe.Admin.service.impl.UserServiceImpl;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AgentController {
    private final UserServiceImpl userService;
    private final AgentServiceImpl agentServiceImpl;
    private Logger log = LoggerFactory.getLogger(AgentController.class);

    @GetMapping("/agent_add/{idUser}")
    public String addAgent(@PathVariable int idUser, Model model){
        model.addAttribute("idUser", idUser);
        model.addAttribute("agent",AgentDTO.builder().build());
        model.addAttribute("typeUser", userService.findById(idUser).getTypeUser());

        return "admin/agent_add";
    }

//    @PostMapping("/agent_add")
//    public String saveAgent(@RequestParam int idUser,@RequestParam String name, @RequestParam String surname, @RequestParam String mail,@RequestParam String number, Model model){
//        Agent agent = Agent.builder().number(number).name(name).surname(surname).mail(mail).build();
//        User user = userService.findById(idUser);
//        List<User> users = new ArrayList<>();
//        users.add(user);
//        agent.setUsers(users);
//        if (user.getTypeUser() == TypeUser.CLIENT){
//            agent.setType(TypeAgent.AGENT);
//        }
//        else if(user.getTypeUser() == TypeUser.CONTRACTOR){
//            agent.setType(TypeAgent.SALES);
//        }
//        agentServiceImpl.saveEntity(agent);
//        user.setAgent(agent);
//        userService.updateEntity(user,idUser);
//        return "redirect:/user_edit/"+idUser;
//    }
    @PostMapping("/agent_add/{idUser}")
    public String saveAgent(@PathVariable int idUser,@Valid @ModelAttribute(name = "agent") AgentDTO agentDTO, BindingResult result,Model model){
        User user = userService.findById(idUser);
        result = agentServiceImpl.uniqueEmail(agentDTO.getMail(),result,0,"add");
        if (result.hasErrors()){
            System.out.println(result.getAllErrors());
            model.addAttribute("idUser", idUser);
            model.addAttribute("agent", agentDTO);
            model.addAttribute("typeUser", user.getTypeUser());
            return "admin/agent_add";
        }
        if(user.getTypeUser().equals(TypeUser.CLIENT)){
            agentDTO.setType(TypeAgent.AGENT);
        }
        else {
            agentDTO.setType(TypeAgent.SALES);
        }
        agentServiceImpl.saveDTO(agentDTO);
        log.info("User id:"+idUser+",agent add");
        return "redirect:/user_edit/"+agentDTO.getIdUser();
    }
    @GetMapping("/agent_edit/{idAgent}")
    public String agentEdit( @PathVariable int idAgent, Model model){
        Agent agent = agentServiceImpl.findById(idAgent);
        model.addAttribute("agent",agent);
        model.addAttribute("idUser", agent.getUsers().get(0).getIdUser());
        model.addAttribute("typeUser",agent.getUsers().get(0).getTypeUser());
        return "admin/agent_edit";
    }
//    @PostMapping("/agent_update/{id}")
//    public String agentUpdate(@PathVariable int id, @RequestParam String name,@RequestParam String surname,@RequestParam String number,@RequestParam String mail,@RequestParam(name = "idUser") int idUser, Model model){
//        Agent agent = Agent.builder().surname(surname).name(name).number(number).mail(mail).build();
//        agentServiceImpl.updateEntity(agent,id);
//        return "redirect:/user_edit/"+idUser;
//    }
    @PostMapping("/agent_edit/{id}")
    public String agentUpdate(@PathVariable int id,@Valid @ModelAttribute(name = "agent") AgentDTO agentDTO,BindingResult result, Model model){
        result = agentServiceImpl.uniqueEmail(agentDTO.getMail(),result,id,"update");
        if (result.hasErrors()){
            model.addAttribute("agent", agentDTO);
            model.addAttribute("idUser", agentDTO.getIdUser());
            model.addAttribute("typeUser", userService.findById(agentDTO.getIdUser()).getTypeUser());
            return "admin/agent_edit";
        }
        agentServiceImpl.updateDTO(agentDTO,id);
        log.info("Agent id:"+id+", was update");
        return "redirect:/user_edit/"+agentDTO.getIdUser();
    }
}
