package com.example.Swipe.Admin.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        if (!Objects.equals(title, news.title)) return false;
        if (!Objects.equals(description, news.description)) return false;
        return Objects.equals(date, news.date);
    }

    @Override
    public int hashCode() {
        int result = idNews;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
