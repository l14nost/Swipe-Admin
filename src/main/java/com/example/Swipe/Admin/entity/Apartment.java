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
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idapartment")
    private int idApartment;

    private int number;

    private TypeApartment type;

    @Column(name = "count_room")
    private CountRoom countRoom;

    private LayoutType layout;

    private State state;

    @Column(name = "total_area")
    private int totalArea;

    @Column(name = "kitchen_area")
    private int kitchenArea;

    @Column(name = "balcony_type")
    private BalconyType balconyType;

    @Column(name = "heating_type")
    private HeatingType heatingType;

    private Calculation calculation;

    @Column(name = "founding_document")
    private FoundingDocument foundingDocument;

    private Commission commission;

    @Column(name = "main_photo")
    private String mainPhoto;

    @Column(name = "communication_type")
    private CommunicationType communicationType;

    private String description;

    private int price;

    @ManyToOne
    @JoinColumn(name = "id_lcd")
    private LCD lcd;

    @ManyToOne
    @JoinColumn(name="id_address")
    private Address address;


    @OneToMany(mappedBy = "apartment")
    private List<Photos> photosList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_frame")
    private Frame frame;

}
