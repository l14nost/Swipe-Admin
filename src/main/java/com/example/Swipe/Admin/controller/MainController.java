package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.service.impl.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private final UserServiceImpl userServiceImpl;
    private final ContractorServiceImpl contractorServiceImpl;
    private final AgentServiceImpl agentServiceImpl;
    private final NotaryServiceImpl notaryServiceImpl;
    private final SalesDepartmentServiceImpl salesDepartmentServiceImpl;

    public MainController(UserServiceImpl userServiceImpl, ContractorServiceImpl contractorServiceImpl, AgentServiceImpl agentServiceImpl, NotaryServiceImpl notaryServiceImpl, SalesDepartmentServiceImpl salesDepartmentServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.contractorServiceImpl = contractorServiceImpl;
        this.agentServiceImpl = agentServiceImpl;
        this.notaryServiceImpl = notaryServiceImpl;
        this.salesDepartmentServiceImpl = salesDepartmentServiceImpl;
    }

    @GetMapping("/")
    public String main(Model model){

        return "admin/admin_main";
    }
}
