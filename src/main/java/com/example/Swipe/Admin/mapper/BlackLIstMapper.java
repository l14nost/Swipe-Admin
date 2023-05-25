package com.example.Swipe.Admin.mapper;

import com.example.Swipe.Admin.dto.BlackListDTO;
import com.example.Swipe.Admin.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class BlackLIstMapper implements Function<User, BlackListDTO> {
    public User toEntity(BlackListDTO userDTO){
        return User.builder()
                .idUser(userDTO.getIdUser())
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .typeUser(userDTO.getTypeUser())
                .mail(userDTO.getMail())
                .build();
    }

    @Override
    public BlackListDTO apply(User user) {
        return BlackListDTO.builder()
                .idUser(user.getIdUser())
                .name(user.getName())
                .mail(user.getMail())
                .surname(user.getSurname())
                .typeUser(user.getTypeUser())
                .build();

    }
}
