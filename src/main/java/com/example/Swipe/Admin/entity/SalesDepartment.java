package com.example.Swipe.Admin.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.Builder;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SalesDepartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idsales_department")
    private int idSalesDepartment;
    private String name;
    private String surname;
    private String number;
    private String mail;
    @OneToOne
    @JoinColumn(name = "id_builder")
    private Contractor contractor;
}
