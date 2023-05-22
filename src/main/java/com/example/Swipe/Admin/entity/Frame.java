package com.example.Swipe.Admin.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
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

    @NotBlank(message = "Input num")
    private int num;

    @ManyToOne
    @JoinColumn(name = "id_lcd")
    private LCD lcd;

    @OneToMany(mappedBy = "frame", cascade = CascadeType.ALL)
    private List<Apartment> apartmentList = new ArrayList<>();

}
