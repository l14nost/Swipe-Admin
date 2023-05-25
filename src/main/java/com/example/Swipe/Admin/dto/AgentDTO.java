package com.example.Swipe.Admin.dto;

import com.example.Swipe.Admin.enums.TypeAgent;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AgentDTO {
    int idAgent;
    int idUser;
    @NotBlank
    @Pattern(regexp = "^[A-Z][a-zA-Z]*$", message = "Имя должно содержать только буквы и начинаться с заглавной буквы")
    String name;
    @NotBlank
    @Pattern(regexp = "^[A-Z][a-zA-Z]*$", message = "Фамилия должно содержать только буквы и начинаться с заглавной буквы")
    String surname;
    @NotBlank
    @Email(message = "Не рправильный формат почты (Ex:you@example.com)", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    String mail;
    @NotBlank
    @Pattern(regexp = "^[0-9]*$", message = "Фамилия должно содержать только буквы и начинаться с заглавной буквы")
    @Size(max = 9, min = 9)
    String number;
    TypeAgent type;

}
