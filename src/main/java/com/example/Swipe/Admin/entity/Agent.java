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
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idagent")
    private int idAgent;

    private String name;
    private String surname;
    private String number;
    private String mail;


}
