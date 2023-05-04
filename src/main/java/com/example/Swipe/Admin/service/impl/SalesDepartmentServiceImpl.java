package com.example.Swipe.Admin.service.impl;

import com.example.Swipe.Admin.entity.Agent;
import com.example.Swipe.Admin.entity.Contractor;
import com.example.Swipe.Admin.entity.SalesDepartment;
import com.example.Swipe.Admin.repository.SalesDepartmentRepo;
import com.example.Swipe.Admin.service.SalesDepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalesDepartmentServiceImpl implements SalesDepartmentService {
    private final SalesDepartmentRepo salesDepartmentRepo;

    public SalesDepartmentServiceImpl(SalesDepartmentRepo salesDepartmentRepo) {
        this.salesDepartmentRepo = salesDepartmentRepo;
    }
    public List<SalesDepartment> salesDepartments(){
        return salesDepartmentRepo.findAll();
    }

    @Override
    public List<SalesDepartment> findAll() {
        return salesDepartmentRepo.findAll();
    }

    @Override
    public SalesDepartment findById(int id) {
        Optional<SalesDepartment> salesDepartment = salesDepartmentRepo.findById(id);
        if(salesDepartment.isPresent()){
            return salesDepartment.get();
        }
        else {
            return SalesDepartment.builder().build();
        }
    }

    @Override
    public void saveEntity(SalesDepartment salesDepartment) {
        salesDepartmentRepo.save(salesDepartment);
    }

    @Override
    public void deleteById(int id) {
        salesDepartmentRepo.deleteById(id);
    }

    @Override
    public void updateEntity(SalesDepartment salesDepartment, int id) {
        Optional<SalesDepartment> salesDepartmentOptional = salesDepartmentRepo.findById(id);
        if(salesDepartmentOptional.isPresent()){
            SalesDepartment salesDepartmentUpdate = salesDepartmentOptional.get();
            if(salesDepartment.getName()!=null){
                salesDepartmentUpdate.setName(salesDepartment.getName());
            }
            if(salesDepartment.getMail()!=null){
                salesDepartmentUpdate.setMail(salesDepartment.getMail());
            }
            if(salesDepartment.getNumber()!=null){
                salesDepartmentUpdate.setNumber(salesDepartment.getNumber());
            }
            if(salesDepartment.getSurname()!=null){
                salesDepartmentUpdate.setSurname(salesDepartment.getSurname());
            }
            salesDepartmentRepo.saveAndFlush(salesDepartment);
        }
        else {
            SalesDepartment salesDepartmentUpdate = SalesDepartment.builder().name("").surname("").mail("").number("").build();
            salesDepartmentRepo.saveAndFlush(salesDepartment);
        }
    }
}
