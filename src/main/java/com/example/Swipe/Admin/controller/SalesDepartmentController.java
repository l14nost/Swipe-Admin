package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.entity.Agent;
import com.example.Swipe.Admin.entity.SalesDepartment;
import com.example.Swipe.Admin.service.impl.SalesDepartmentServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SalesDepartmentController {
    private final SalesDepartmentServiceImpl salesDepartmentServiceImpl;

    public SalesDepartmentController(SalesDepartmentServiceImpl salesDepartmentServiceImpl) {
        this.salesDepartmentServiceImpl = salesDepartmentServiceImpl;
    }
    @GetMapping("/sales_department_edit/{id}")
    public String salesDepartmentEdit(@PathVariable String id, Model model){
        String[] idList = id.split("_");
        model.addAttribute("salesDepartment",salesDepartmentServiceImpl.findById(Integer.valueOf(idList[0])));
        model.addAttribute("idContractor", Integer.valueOf(idList[1]));
        return "admin/sales_department_edit";
    }
    @PostMapping("/sales_department_update/{id}")
    public String salesDepartmentUpdate(@PathVariable int id, @RequestParam String name, @RequestParam String surname, @RequestParam String number, @RequestParam String mail, @RequestParam(name = "idContractor") int idContractor, Model model){
        SalesDepartment salesDepartment = SalesDepartment.builder().surname(surname).name(name).number(number).mail(mail).build();
        salesDepartmentServiceImpl.updateEntity(salesDepartment,id);
        return "redirect:/contractor_edit/"+idContractor;
    }
}
