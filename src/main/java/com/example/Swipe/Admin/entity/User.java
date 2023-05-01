package com.example.Swipe.Admin.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.Builder;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser")
    private int idUser;

    private String name;

    private String surname;

    private String number;

    private String mail;

    private String password;

    @Column(name = "type_notification")
    private String typeNotification;

    @Column(name = "date_sub")
    private LocalDate dateSub;

    @Column(name = "call_sms")
    private boolean callSms;

    @ManyToOne
    private Agent agent;
}
