package com.example.Swipe.Admin.repository;

import com.example.Swipe.Admin.entity.Address;
import com.example.Swipe.Admin.entity.SalesDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesDepartmentRepo extends JpaRepository<SalesDepartment, Integer> {
}
