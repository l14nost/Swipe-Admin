package com.example.Swipe.Admin.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Frame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idframe")
    private int idFrame;

    private int num;

    @ManyToOne
    @JoinColumn(name = "id_lcd")
    private LCD lcd;

    @OneToMany(mappedBy = "frame")
    private List<Apartment> apartmentList = new ArrayList<>();

}
