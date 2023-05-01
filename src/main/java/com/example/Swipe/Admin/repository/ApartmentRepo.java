package com.example.Swipe.Admin.repository;

import com.example.Swipe.Admin.entity.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentRepo extends JpaRepository<Apartment, Integer> {
}
