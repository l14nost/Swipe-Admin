package com.example.Swipe.Admin.entity;

import com.example.Swipe.Admin.enums.*;
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

    @Column(name = "main_photo")
    private String mainPhoto;

    private String name;

    private String description;

    private StatusLCDType status;

    @Column(name = "class")
    private ClassType lcdClass;

    private LCDType type;

    private TechnologyType technology;

    private TerritoryType territory;

    @Column(name = "distance_sea")
    private int distanceSea;

    private CommunalType communal;

    private int height;

    private GasType gas;

    private HeatingType heating;

    private HeatingType sewerage;

    @Column(name = "water_supply")
    private HeatingType waterSupply;

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

    @OneToMany(mappedBy = "lcd")
    private List<Frame> frames = new ArrayList<>();

}
