package com.example.Swipe.Admin.dto;

import com.example.Swipe.Admin.enums.TypeAgent;
import com.example.Swipe.Admin.validation.UniqueEmailAgent;
import javax.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AgentDTO {
    int idAgent;
    int idUser;
    @NotBlank
    @Pattern(regexp = "^[А-ЯЁA-Z][а-яёa-zA-Z]*$", message = "Имя должно содержать только буквы и начинаться с заглавной буквы")
    @Size(min = 2, max = 50)
    String name;
    @NotBlank
    @Pattern(regexp = "^[А-ЯЁA-Z][а-яёa-zA-Z]*$", message = "Фамилия должно содержать только буквы и начинаться с заглавной буквы")
    @Size(min = 2, max = 50)
    String surname;
    @NotBlank
    @Pattern(message = "Неправильный формат почты (Ex:you@example.com)", regexp = "^((([0-9A-Za-z]{1}[-0-9A-z\\.]{0,30}[0-9A-Za-z]?)|([0-9А-Яа-я]{1}[-0-9А-я\\.]{0,30}[0-9А-Яа-я]?))@([-A-Za-z]{1,}\\.){1,}[-A-Za-z]{2,})$")
//    @UniqueEmailAgent
    @Size(min = 2, max = 50)
    String mail;
    @NotBlank
    @Pattern(regexp = "^[0-9]*$", message = "Номер должен содержать 9 цифр")
    @Size(max = 9, min = 9)
    String number;
    TypeAgent type;

}
