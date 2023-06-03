package com.example.Swipe.Admin.entity;

import com.example.Swipe.Admin.enums.TypeAgent;
import jakarta.persistence.*;
import lombok.*;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idagent")
    private int idAgent;

    private String name;

    private String surname;

    private String number;

    private String mail;

    @Enumerated(EnumType.STRING)
    private TypeAgent type;

    @OneToMany(mappedBy = "agent")
    private List<User> users = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Agent agent = (Agent) o;

        if (idAgent != agent.idAgent) return false;
        if (!Objects.equals(name, agent.name)) return false;
        if (!Objects.equals(surname, agent.surname)) return false;
        if (!Objects.equals(number, agent.number)) return false;
        if (!Objects.equals(mail, agent.mail)) return false;
        return type == agent.type;
    }

    @Override
    public int hashCode() {
        int result = idAgent;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
