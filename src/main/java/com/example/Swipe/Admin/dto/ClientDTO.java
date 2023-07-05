package com.example.Swipe.Admin.dto;

import com.example.Swipe.Admin.enums.TypeUser;
import com.example.Swipe.Admin.validation.FileExtension;
import com.example.Swipe.Admin.validation.UniqueEmail;

import javax.validation.Valid;
import javax.validation.constraints.*;
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

    @NotBlank(message = "Не должно быть пустым")
    @Pattern(regexp = "^[А-ЯЁA-Z][а-яёa-z]*$", message = "Должно содержать только буквы, начинаться и иметь только одну заглавную букву")
    @Size(min = 2, max = 50, message = "Размер имени должен быть меньше 50 и больше 2")
    String name;

    @NotBlank(message = "Не должно быть пустым")
    @Pattern(regexp = "^[А-ЯЁA-Z][а-яёa-z]*$", message = "Должно содержать только буквы, начинаться и иметь только одну заглавную букву")
    @Size(min = 2, max = 50, message = "Размер фамилии должен быть меньше 50 и больше 2")
    String surname;

    @NotBlank(message = "Не должно быть пустым")
    @Pattern(message = "Неправильный формат почты (Ex:you@example.com)", regexp = "^((([0-9A-Za-z]{1}[-0-9A-z\\.]{0,30}[0-9A-Za-z]?))@([-A-Za-z]{1,}\\.){1,}[-A-Za-z]{2,})$")
//    @UniqueEmail
    @Size(min = 5, max = 255, message = "Размер почты должен быть меньше 255 и больше 5")
    String mail;


//    boolean blackList;
    @FileExtension(value = {"jpg", "png"}, message = "Формат не корректный (.jpg,.png)")
    MultipartFile filename;
    @NotBlank(message = "Не должно быть пустым")
    @Pattern(regexp = "^[0-9]*$", message = "Номер должен содержать 9 цифр")
    @Size(max = 9, min = 9, message = "Размер номера должен быть равен 9")
    String number;
    String photo;

    TypeUser type;
    @Valid
    UserAddInfoDTO userAddInfoDTO;
    @Valid
    AgentDTO agent;

}
