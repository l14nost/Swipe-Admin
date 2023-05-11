package com.example.Swipe.Admin.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.Builder;

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

    @ManyToOne
    @JoinColumn(name = "id_lcd")
    private LCD lcd;

    @ManyToOne
    @JoinColumn(name = "id_apartment")
    private Apartment apartment;
}
