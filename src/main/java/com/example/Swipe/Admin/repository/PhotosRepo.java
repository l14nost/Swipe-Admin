package com.example.Swipe.Admin.repository;

import com.example.Swipe.Admin.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotosRepo extends JpaRepository<Photo, Integer> {
}
