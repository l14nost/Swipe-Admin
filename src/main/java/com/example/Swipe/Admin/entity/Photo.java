package com.example.Swipe.Admin.entity;

import javax.persistence.*;
import lombok.*;
import lombok.Builder;

import java.util.Objects;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idphotos")
    private int idPhotos;

    @Column(name = "filename")
    private String fileName;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_lcd")
    private LCD lcd;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_apartment")
    private Apartment apartment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Photo photo = (Photo) o;

        return idPhotos == photo.idPhotos;
    }

    @Override
    public int hashCode() {
        return idPhotos;
    }
}
