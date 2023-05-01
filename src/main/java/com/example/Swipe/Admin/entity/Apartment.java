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
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idapartment")
    private int idApartment;

    private String type;

    @Column(name = "count_room")
    private int countRoom;

    private String layout;

    private String state;

    @Column(name = "total_area")
    private int totalArea;

    @Column(name = "kitchen_area")
    private int kitchenArea;

    @Column(name = "balcony_type")
    private String balconyType;

    @Column(name = "heating_type")
    private String heatingType;

    private String calculation;

    private int commission;

    @Column(name = "communication_type")
    private String communicationType;

    private String description;

    private int price;

    @ManyToOne
    @JoinColumn(name = "id_lcd")
    private LCD lcd;

    @OneToMany(mappedBy = "apartment")
    private List<Photos> photosList = new ArrayList<>();


}
