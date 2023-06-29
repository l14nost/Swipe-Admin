package com.example.Swipe.Admin.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
public class AdminDto {
    int idUser;
    @Size(min = 5,max = 255)
    @Pattern(message = "Неправильный формат почты (Ex:you@example.com)", regexp = "^((([0-9A-Za-z]{1}[-0-9A-z\\.]{0,30}[0-9A-Za-z]?)|([0-9А-Яа-я]{1}[-0-9А-я\\.]{0,30}[0-9А-Яа-я]?))@([-A-Za-z]{1,}\\.){1,}[-A-Za-z]{2,})$")
    String mail;
    @Size(max = 255)
    String password;
    @Size(max = 255)
    String confirmPassword;
}
