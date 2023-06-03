package com.example.Swipe.Admin.repository;

import com.example.Swipe.Admin.entity.Apartment;
import com.example.Swipe.Admin.entity.User;
import com.example.Swipe.Admin.enums.TypeUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> , JpaSpecificationExecutor<User> {
    Optional<User> findByMail(String mail);
    List<User> findAllByMail(String mail);
    List<User> findAllByTypeUserAndBlackListIsFalse(TypeUser typeUser);
    Page<User> findAllByBlackListIsTrue(Pageable pageable);

    Page<User> findAllByTypeUserAndBlackListIsFalse(TypeUser typeUser, Pageable pageable);
    int countByMail(String email);

}
