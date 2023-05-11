package com.example.Swipe.Admin.entity;

import com.example.Swipe.Admin.enums.TypeNotification;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserAddInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser_add_info")
    private int idUserAddInfo;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_notification")
    private TypeNotification typeNotification;

    @Column(name = "date_sub")
    private LocalDate dateSub;

    @Column(name = "call_sms")
    private boolean callSms;
}
