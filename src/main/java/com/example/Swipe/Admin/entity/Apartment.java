package com.example.Swipe.Admin.entity;

import com.example.Swipe.Admin.enums.*;
import javax.persistence.*;
import lombok.*;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private String address;

    @ManyToOne
    @JoinColumn(name = "id_lcd")
    private LCD lcd;


    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL)
    private List<Photo> photoList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_frame")
    private Frame frame;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Apartment apartment = (Apartment) o;

        if (number != apartment.number) return false;
        if (totalArea != apartment.totalArea) return false;
        if (kitchenArea != apartment.kitchenArea) return false;
        if (price != apartment.price) return false;
        if (type != apartment.type) return false;
        if (countRoom != apartment.countRoom) return false;
        if (layout != apartment.layout) return false;
        if (state != apartment.state) return false;
        if (balconyType != apartment.balconyType) return false;
        if (heatingType != apartment.heatingType) return false;
        if (calculation != apartment.calculation) return false;
        if (foundingDocument != apartment.foundingDocument) return false;
        if (commission != apartment.commission) return false;
        if (communicationType != apartment.communicationType) return false;
        return Objects.equals(description, apartment.description);
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (countRoom != null ? countRoom.hashCode() : 0);
        result = 31 * result + (layout != null ? layout.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + totalArea;
        result = 31 * result + kitchenArea;
        result = 31 * result + (balconyType != null ? balconyType.hashCode() : 0);
        result = 31 * result + (heatingType != null ? heatingType.hashCode() : 0);
        result = 31 * result + (calculation != null ? calculation.hashCode() : 0);
        result = 31 * result + (foundingDocument != null ? foundingDocument.hashCode() : 0);
        result = 31 * result + (commission != null ? commission.hashCode() : 0);
        result = 31 * result + (communicationType != null ? communicationType.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + price;
        return result;
    }
}
