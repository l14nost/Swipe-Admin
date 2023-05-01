package com.example.Swipe.Admin.service;

import com.example.Swipe.Admin.repository.SalesDepartmentRepo;
import org.springframework.stereotype.Service;

@Service
public class SalesDepartmentService {
    private final SalesDepartmentRepo salesDepartmentRepo;

    public SalesDepartmentService(SalesDepartmentRepo salesDepartmentRepo) {
        this.salesDepartmentRepo = salesDepartmentRepo;
    }
}
