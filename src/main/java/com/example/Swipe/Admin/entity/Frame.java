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

    @OneToMany(mappedBy = "frame", cascade = CascadeType.ALL)
    private List<Apartment> apartmentList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Frame frame = (Frame) o;

        if (idFrame != frame.idFrame) return false;
        return num == frame.num;
    }

    @Override
    public int hashCode() {
        int result = idFrame;
        result = 31 * result + num;
        return result;
    }
}
