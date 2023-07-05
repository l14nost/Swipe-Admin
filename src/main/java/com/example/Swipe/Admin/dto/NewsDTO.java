package com.example.Swipe.Admin.dto;

import com.example.Swipe.Admin.entity.LCD;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
public class NewsDTO {

    int idNews;
    @NotBlank(message = "Не должно быть пустым")
    @Size(min = 2, max = 50, message = "Размер загаловка должен быть меньше 50 и больше 2")
    String title;
    @NotBlank(message = "Не должно быть пустым")
    @Size(min = 5, max = 255, message = "Размер описания должен быть меньше 255 и больше 5")
    String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Не должно быть пустым")
    LocalDate date;

    int idLcd;
}
