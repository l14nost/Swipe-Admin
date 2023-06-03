package com.example.Swipe.Admin.entity;

import com.example.Swipe.Admin.enums.*;
import jakarta.persistence.*;
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
@ToString
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
//    @Convert(converter = AdvantageConverter.class)
    private String advantages;

    @Column(name = "type_payment")
    private String typePayment;

    private String appointment;

    @Column(name = "sum_contract")
    private String sumContract;

    private String formalization;
    private String address;
    @OneToOne
    @JoinColumn(name = "id_contractor")
    private User user;

    @OneToMany(mappedBy = "lcd", cascade = CascadeType.ALL)
    private List<News> newsList = new ArrayList<>();

    @OneToMany(mappedBy = "lcd", cascade = CascadeType.ALL)
    private List<Photo> photoList = new ArrayList<>();

    @OneToMany(mappedBy = "lcd")
    private List<Apartment> apartmentList = new ArrayList<>();

    @OneToMany(mappedBy = "lcd", cascade = CascadeType.ALL)
    private List<Documents> documents = new ArrayList<>();

    @OneToMany(mappedBy = "lcd", cascade = CascadeType.ALL)
    private List<Frame> frames = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LCD lcd = (LCD) o;

        if (idLcd != lcd.idLcd) return false;
        if (distanceSea != lcd.distanceSea) return false;
        if (height != lcd.height) return false;
        if (!Objects.equals(name, lcd.name)) return false;
        if (!Objects.equals(description, lcd.description)) return false;
        if (status != lcd.status) return false;
        if (lcdClass != lcd.lcdClass) return false;
        if (type != lcd.type) return false;
        if (technology != lcd.technology) return false;
        if (territory != lcd.territory) return false;
        if (communal != lcd.communal) return false;
        if (gas != lcd.gas) return false;
        if (heating != lcd.heating) return false;
        if (sewerage != lcd.sewerage) return false;
        if (waterSupply != lcd.waterSupply) return false;
        if (!Objects.equals(advantages, lcd.advantages)) return false;
        if (!Objects.equals(typePayment, lcd.typePayment)) return false;
        if (!Objects.equals(appointment, lcd.appointment)) return false;
        if (!Objects.equals(sumContract, lcd.sumContract)) return false;
        if (!Objects.equals(formalization, lcd.formalization)) return false;
        if (!Objects.equals(user, lcd.user)) return false;
        if (!Objects.equals(newsList, lcd.newsList)) return false;
        if (!Objects.equals(photoList, lcd.photoList)) return false;
        if (!Objects.equals(apartmentList, lcd.apartmentList)) return false;
        if (!Objects.equals(documents, lcd.documents)) return false;
        return Objects.equals(frames, lcd.frames);
    }

    @Override
    public int hashCode() {
        int result = idLcd;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (lcdClass != null ? lcdClass.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (technology != null ? technology.hashCode() : 0);
        result = 31 * result + (territory != null ? territory.hashCode() : 0);
        result = 31 * result + distanceSea;
        result = 31 * result + (communal != null ? communal.hashCode() : 0);
        result = 31 * result + height;
        result = 31 * result + (gas != null ? gas.hashCode() : 0);
        result = 31 * result + (heating != null ? heating.hashCode() : 0);
        result = 31 * result + (sewerage != null ? sewerage.hashCode() : 0);
        result = 31 * result + (waterSupply != null ? waterSupply.hashCode() : 0);
        result = 31 * result + (advantages != null ? advantages.hashCode() : 0);
        result = 31 * result + (typePayment != null ? typePayment.hashCode() : 0);
        result = 31 * result + (appointment != null ? appointment.hashCode() : 0);
        result = 31 * result + (sumContract != null ? sumContract.hashCode() : 0);
        result = 31 * result + (formalization != null ? formalization.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (newsList != null ? newsList.hashCode() : 0);
        result = 31 * result + (photoList != null ? photoList.hashCode() : 0);
        result = 31 * result + (apartmentList != null ? apartmentList.hashCode() : 0);
        result = 31 * result + (documents != null ? documents.hashCode() : 0);
        result = 31 * result + (frames != null ? frames.hashCode() : 0);
        return result;
    }
}
