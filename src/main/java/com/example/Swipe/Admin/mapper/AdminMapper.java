package com.example.Swipe.Admin.mapper;

import com.example.Swipe.Admin.dto.AdminDto;
import com.example.Swipe.Admin.dto.ClientDTO;
import com.example.Swipe.Admin.entity.User;
import com.example.Swipe.Admin.entity.UserAddInfo;
import com.example.Swipe.Admin.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
public class AdminMapper {

    public static User toEntity(AdminDto adminDto) {

        User user = User.builder()
                .idUser(adminDto.getIdUser())
                .role(Role.ADMIN)
                .mail(adminDto.getMail())
                .blackList(false)
                .number(adminDto.getPassword())
                .build();
        return user;
    }

    public static AdminDto apply(User user) {

        return AdminDto.builder().mail(user.getMail()).password(user.getPassword()).idUser(user.getIdUser()).build();

    }
}
