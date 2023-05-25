package com.example.Swipe.Admin.mapper;

import com.example.Swipe.Admin.dto.AgentDTO;
import com.example.Swipe.Admin.dto.NewsDTO;
import com.example.Swipe.Admin.entity.Agent;
import com.example.Swipe.Admin.entity.News;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
@RequiredArgsConstructor
public class NewsMapper implements Function<News, NewsDTO> {
    public Agent toEntity(AgentDTO agentDTO){
//        List<User> users = new ArrayList<>();
//        users.add(userService.findById(agentDTO.getIdUser()));
        Agent agent = Agent.builder()
                .name(agentDTO.getName())
                .number(agentDTO.getNumber())
                .mail(agentDTO.getMail())
                .surname(agentDTO.getSurname())
//                .users(users)
                .type(agentDTO.getType())
                .build();
        if (agentDTO.getIdAgent()!=0){
            agent.setIdAgent(agentDTO.getIdAgent());
        }
        return agent;

    }

    @Override
    public NewsDTO apply(News news) {
        return NewsDTO.builder()
                .idNews(news.getIdNews())
                .title(news.getTitle())
                .description(news.getDescription())
                .date(news.getDate())
                .build();

    }
}
