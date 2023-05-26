package com.example.Swipe.Admin.dto;

import com.example.Swipe.Admin.entity.LCD;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

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

    LocalDate date;

    int idLcd;
}
