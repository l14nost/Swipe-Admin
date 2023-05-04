package com.example.Swipe.Admin.entity;

import com.example.Swipe.Admin.enums.TypeNotification;
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

    private String filename;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_notification")
    private TypeNotification typeNotification;

    @Column(name = "date_sub")
    private LocalDate dateSub;

    @Column(name = "call_sms")
    private boolean callSms;

    @ManyToOne
    private Agent agent;
}
