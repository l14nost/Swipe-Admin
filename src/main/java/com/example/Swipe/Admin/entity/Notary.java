package com.example.Swipe.Admin.entity;

import jakarta.persistence.*;
import lombok.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Notary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idnotary")
    private int idNotary;

    private String name;

    private String surname;

    private String mail;

    private String number;


}
