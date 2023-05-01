package com.example.Swipe.Admin.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.Builder;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idnews")
    private int idNews;

    private String title;

    private String description;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "id_lcd")
    private LCD lcd;
}
