package com.example.Swipe.Admin.mapper;

import com.example.Swipe.Admin.dto.ClientDTO;

import com.example.Swipe.Admin.entity.User;
import com.example.Swipe.Admin.enums.Role;
import com.example.Swipe.Admin.enums.TypeUser;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class ClientMapper implements Function<User, ClientDTO> {
    public User toEntity(ClientDTO userDTO){
        return User.builder()
//                .idUser(userDTO.getIdUser())
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .typeUser(TypeUser.CLIENT)
                .role(Role.USER)
                .mail(userDTO.getMail())
                .filename(userDTO.getFilename())
                .build();
    }

    @Override
    public ClientDTO apply(User user) {
        return ClientDTO.builder()
                .name(user.getName())
                .mail(user.getMail())
                .surname(user.getSurname())
                .build();

    }
}
