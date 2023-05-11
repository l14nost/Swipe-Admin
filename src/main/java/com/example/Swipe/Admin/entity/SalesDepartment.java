//package com.example.Swipe.Admin.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//import lombok.Builder;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Builder
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//public class SalesDepartment {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "idsales_department")
//    private int idSalesDepartment;
//
//    private String name;
//
//    private String surname;
//
//    private String number;
//
//    private String mail;
//
//    @OneToMany(mappedBy = "salesDepartment")
//    private List<Contractor> contractors = new ArrayList<>();
//}
