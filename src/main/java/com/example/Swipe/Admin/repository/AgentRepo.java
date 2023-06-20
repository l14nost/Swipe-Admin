package com.example.Swipe.Admin.repository;

import com.example.Swipe.Admin.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentRepo extends JpaRepository<Agent, Integer>{
    int countByMail(String email);

    List<Agent> findAllByMail(String email);
}
