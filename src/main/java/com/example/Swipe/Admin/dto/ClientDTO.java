package com.example.Swipe.Admin.dto;

import com.example.Swipe.Admin.enums.TypeUser;
import com.example.Swipe.Admin.validation.FileExtension;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    int idUser;

    @NotBlank
    @Pattern(regexp = "^[А-ЯЁA-Z][а-яёa-zA-Z]*$", message = "Имя должно содержать только буквы и начинаться с заглавной буквы")
    String name;

    @NotBlank
    @Pattern(regexp = "^[A-Z][a-zA-Z]*$", message = "Фамилия должно содержать только буквы и начинаться с заглавной буквы")
    String surname;

    @NotBlank
    @Email(message = "Не рправильный формат почты (Ex:you@example.com)", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    String mail;

//    boolean blackList;
    @FileExtension(value = {"jpg", "png"}, message = "Формат не корректный (.jpg,.png)")
    MultipartFile filename;
    @NotBlank
    @Pattern(regexp = "^[0-9]*$")
    @Size(max = 9, min = 9)
    String number;
    String photo;

    TypeUser type;

    UserAddInfoDTO userAddInfoDTO;
    AgentDTO agent;

}
