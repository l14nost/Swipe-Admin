package com.example.Swipe.Admin.mapper;

import com.example.Swipe.Admin.dto.AgentDTO;
import com.example.Swipe.Admin.dto.NewsDTO;
import com.example.Swipe.Admin.entity.Agent;
import com.example.Swipe.Admin.entity.News;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@RequiredArgsConstructor
public class NewsMapper  {
    public static News toEntity(NewsDTO newsDTO){
        return News.builder()
                .description(newsDTO.getDescription())
                .title(newsDTO.getTitle())
                .date(newsDTO.getDate())
                .build();

    }

    public static NewsDTO apply(News news) {
        return NewsDTO.builder()
                .idNews(news.getIdNews())
                .title(news.getTitle())
                .description(news.getDescription())
                .date(news.getDate())
                .idLcd(news.getLcd().getIdLcd())
                .build();

    }
}
