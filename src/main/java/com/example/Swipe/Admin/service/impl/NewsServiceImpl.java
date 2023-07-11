package com.example.Swipe.Admin.service.impl;

import com.example.Swipe.Admin.dto.NewsDTO;
import com.example.Swipe.Admin.entity.LCD;
import com.example.Swipe.Admin.entity.News;
import com.example.Swipe.Admin.mapper.FrameMapper;
import com.example.Swipe.Admin.mapper.NewsMapper;
import com.example.Swipe.Admin.repository.NewsRepo;
import com.example.Swipe.Admin.service.NewsService;
import com.example.Swipe.Admin.specification.FrameSpecification;
import com.example.Swipe.Admin.specification.NewsSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private Logger log = LoggerFactory.getLogger(NewsServiceImpl.class);
    private final NewsRepo newsRepo;
    private final LCDServiceImpl lcdService;


    public int countNews(int monthNum){
        int count = 0;
        List<News> news = newsRepo.findAll();
        for (News news1 : news
        ) {
            if(news1.getDate().getMonthValue() == monthNum){
                count++;
            }
        }
        return count;
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
            return null;
        }
    }
    public NewsDTO findByIdDTO(int id) {
        Optional<News> news = newsRepo.findById(id);
        if(news.isPresent()){
            return  NewsMapper.apply(news.get());
        }
        else {
            log.info("News by id"+id+"not found");
            return null;

        }
    }
    public void saveDTO(NewsDTO newsDTO) {
        News news = NewsMapper.toEntity(newsDTO);
        news.setLcd(lcdService.findById(newsDTO.getIdLcd()));
        newsRepo.save(news);
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
        Optional<News> newsOptional = newsRepo.findById(id);
        if(newsOptional.isPresent()){
            News updateNews = newsOptional.get();
            if(news.getDate()!=null){
                updateNews.setDate(news.getDate());
            }
            if(news.getLcd()!=null){
                updateNews.setLcd(news.getLcd());
            }
            if(news.getDescription()!=null){
                updateNews.setDescription(news.getDescription());
            }
            if(news.getTitle()!=null){
                updateNews.setTitle(news.getTitle());
            }
            newsRepo.saveAndFlush(updateNews);
        }

    }
    public void updateDTO(NewsDTO newsDto, int id) {
        News news = NewsMapper.toEntity(newsDto);
        Optional<News> newsOptional = newsRepo.findById(id);
        if(newsOptional.isPresent()){
            News updateNews = newsOptional.get();
            if(news.getDate()!=null){
                updateNews.setDate(news.getDate());
            }
            if(news.getDescription()!=null){
                updateNews.setDescription(news.getDescription());
            }
            if(news.getTitle()!=null){
                updateNews.setTitle(news.getTitle());
            }
            updateNews.setLcd(LCD.builder().idLcd(newsDto.getIdLcd()).build());

            newsRepo.saveAndFlush(updateNews);
        }

    }

    public int count() {
        return (int) newsRepo.count();
    }

    public Page<NewsDTO> pagination(Pageable pageable, String keyWord, String sortedBy, int order) {
        if(!keyWord.equals( "null")){
            NewsSpecification newsSpecification = NewsSpecification.builder().keyWord(keyWord).order(order).sortedBy(sortedBy).build();
            return newsRepo.findAll(newsSpecification,pageable).map(NewsMapper::apply);
        }
        NewsSpecification newsSpecification = NewsSpecification.builder().order(order).sortedBy(sortedBy).build();
        return newsRepo.findAll(newsSpecification,pageable).map(NewsMapper::apply);
    }
}
