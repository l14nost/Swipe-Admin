package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.service.impl.SalesDepartmentServiceImpl;
import org.springframework.stereotype.Controller;

@Controller
public class SalesDepartmentController {
    private final SalesDepartmentServiceImpl salesDepartmentServiceImpl;

    public SalesDepartmentController(SalesDepartmentServiceImpl salesDepartmentServiceImpl) {
        this.salesDepartmentServiceImpl = salesDepartmentServiceImpl;
    }
}
