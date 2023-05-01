package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.service.SalesDepartmentService;
import org.springframework.stereotype.Controller;

@Controller
public class SalesDepartmentController {
    private final SalesDepartmentService salesDepartmentService;

    public SalesDepartmentController(SalesDepartmentService salesDepartmentService) {
        this.salesDepartmentService = salesDepartmentService;
    }
}
