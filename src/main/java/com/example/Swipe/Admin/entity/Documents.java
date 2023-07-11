package com.example.Swipe.Admin.entity;

import javax.persistence.*;
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

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_lcd")
    private LCD lcd;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Documents documents = (Documents) o;

        return idDocuments == documents.idDocuments;
    }

    @Override
    public int hashCode() {
        return idDocuments;
    }
}
