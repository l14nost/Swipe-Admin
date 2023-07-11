package com.example.Swipe.Admin.repository;

import com.example.Swipe.Admin.entity.Apartment;
import com.example.Swipe.Admin.entity.Frame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FrameRepo extends JpaRepository<Frame,Integer>, JpaSpecificationExecutor<Frame> {
}
