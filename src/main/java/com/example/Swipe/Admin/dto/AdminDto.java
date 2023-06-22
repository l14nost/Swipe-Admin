package com.example.Swipe.Admin.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminDto {
    int idUser;
    String mail;
    String password;
    String confirmPassword;
}
