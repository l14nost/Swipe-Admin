package com.example.Swipe.Admin.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
public class AdminDto {
    int idUser;
    @Size(min = 5,max = 100, message = "Размер почты должен быть меньше 100 символов и больше 5")
    @Pattern(message = "Неправильный формат почты (Ex:you@example.com)", regexp = "^((([0-9A-Za-z]{1}[-0-9A-z\\.]{0,30}[0-9A-Za-z]?))@([-A-Za-z]{1,}\\.){1,}[-A-Za-z]{2,})$")
    String mail;
    @Size(max = 255, message = "Размер пароля должен быть меньше 255 символов")
    String password;
    @Size(max = 255, message = "Размер пароля должен быть меньше 255 символов")
    String confirmPassword;
}
