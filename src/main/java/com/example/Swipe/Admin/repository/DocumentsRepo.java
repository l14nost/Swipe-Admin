package com.example.Swipe.Admin.repository;

import com.example.Swipe.Admin.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentsRepo extends JpaRepository<Address, Integer> {
}
