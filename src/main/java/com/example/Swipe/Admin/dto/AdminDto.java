package com.example.Swipe.Admin.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@Builder
public class AdminDto {
    int idUser;
    @Size(min = 5,max = 255)
    String mail;
    @Size(max = 255)
    String password;
    @Size(max = 255)
    String confirmPassword;
}
