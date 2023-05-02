package com.example.Swipe.Admin.repository;

import com.example.Swipe.Admin.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AgentRepo extends JpaRepository<Agent, Integer>{
}
