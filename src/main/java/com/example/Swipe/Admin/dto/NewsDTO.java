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
    String title;
    @NotBlank
    @Size(min = 5, max = 1000)
    String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate date;

    int idLcd;
}
