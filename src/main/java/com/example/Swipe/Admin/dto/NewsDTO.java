package com.example.Swipe.Admin.dto;

import com.example.Swipe.Admin.entity.LCD;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class NewsDTO {

    private int idNews;

    private String title;

    private String description;

    private LocalDate date;

    private LcdDTO lcd;
}
