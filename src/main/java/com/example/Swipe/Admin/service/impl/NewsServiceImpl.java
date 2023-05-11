package com.example.Swipe.Admin.service.impl;

import com.example.Swipe.Admin.entity.News;
import com.example.Swipe.Admin.repository.NewsRepo;
import com.example.Swipe.Admin.service.NewsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService {
    private final NewsRepo newsRepo;

    public NewsServiceImpl(NewsRepo newsRepo) {
        this.newsRepo = newsRepo;
    }

    @Override
    public List<News> findAll() {
        return newsRepo.findAll();
    }

    @Override
    public News findById(int id) {
        Optional<News> news = newsRepo.findById(id);
        if(news.isPresent()){
            return  news.get();
        }
        else {
            return News.builder().build();
        }
    }

    @Override
    public void saveEntity(News news) {
        newsRepo.save(news);
    }

    @Override
    public void deleteById(int id) {
        newsRepo.deleteById(id);
    }

    @Override
    public void updateEntity(News news, int id) {

    }
}
