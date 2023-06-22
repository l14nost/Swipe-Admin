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
    @NotBlank
    @Size(min = 2, max = 50)
    String title;
    @NotBlank
    @Size(min = 5, max = 255)
    String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate date;

    int idLcd;
}
