package com.example.Swipe.Admin.repository;

import com.example.Swipe.Admin.entity.Apartment;
import com.example.Swipe.Admin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
}
