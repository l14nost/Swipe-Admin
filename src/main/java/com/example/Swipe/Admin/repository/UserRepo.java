package com.example.Swipe.Admin.repository;

import com.example.Swipe.Admin.entity.Apartment;
import com.example.Swipe.Admin.entity.User;
import com.example.Swipe.Admin.enums.TypeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByMail(String mail);
    List<User> findAllByTypeUser(TypeUser typeUser);
}
