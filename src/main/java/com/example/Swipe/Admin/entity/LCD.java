package com.example.Swipe.Admin.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LCD {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlcd")
    private int idLcd;

    private String name;

    private String status;

    private String type;

    private String technology;

    private String territory;

    @Column(name = "distance_sea")
    private int distanceSea;

    private String communal;

    private int height;

    private String gas;

    private String heating;

    private String sewerage;

    @Column(name = "water_supply")
    private String waterSupply;

    private String advantages;

    @Column(name = "type_payment")
    private String typePayment;

    private String appointment;

    @Column(name = "sum_contract")
    private int sumContract;

    @OneToOne
    @JoinColumn(name = "builder_id")
    private Contractor contractor;

    @OneToMany(mappedBy = "lcd")
    private List<News> newsList = new ArrayList<>();

    @OneToMany(mappedBy = "lcd")
    private List<Photos> photosList = new ArrayList<>();

    @OneToMany(mappedBy = "lcd")
    private List<Apartment> apartmentList = new ArrayList<>();

    @OneToMany(mappedBy = "lcd")
    private List<Documents> documents = new ArrayList<>();

}
