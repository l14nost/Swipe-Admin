package com.example.Swipe.Admin.repository;

import com.example.Swipe.Admin.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepo extends JpaRepository<News, Integer> {
}
