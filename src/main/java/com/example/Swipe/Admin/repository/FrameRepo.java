package com.example.Swipe.Admin.repository;

import com.example.Swipe.Admin.entity.Address;
import com.example.Swipe.Admin.entity.Apartment;
import com.example.Swipe.Admin.entity.Frame;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrameRepo extends JpaRepository<Frame,Integer>{
}
