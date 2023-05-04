package com.example.Swipe.Admin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@lombok.Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Contractor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_builder")
    private int idBuilder;

    private String name;

    private String surname;

    private String mail;

    private String filename;
    @OneToOne
    @JoinColumn(name = "id_lcd")
    private LCD lcd;

    @ManyToOne
    private SalesDepartment salesDepartment;
}
