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
public class Documents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddocuments")
    private int idDocuments;

    private String name;

    private String fileName;

    @ManyToOne
    @JoinColumn(name = "id_builder")
    private Contractor contractor;

}
