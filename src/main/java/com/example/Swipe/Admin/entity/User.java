package com.example.Swipe.Admin.entity;

import com.example.Swipe.Admin.enums.Role;
import com.example.Swipe.Admin.enums.TypeNotification;
import com.example.Swipe.Admin.enums.TypeUser;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.*;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser")
    private int idUser;
    private String name;

    private String password;
    private String surname;

    private String number;

    private String mail;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private TypeUser typeUser;

    private String filename;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "type_notification")
//    private TypeNotification typeNotification;

//    @Column(name = "date_sub")
//    private LocalDate dateSub;

//    @Column(name = "call_sms")
//    private boolean callSms;

    @ManyToOne
    private Agent agent;

    @OneToOne(cascade = CascadeType.ALL)
    private UserAddInfo userAddInfo;

    @OneToMany(mappedBy = "user",cascade = CascadeType.MERGE)
    private List<Apartment> apartment;


    private boolean blackList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return mail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!Objects.equals(name, user.name)) return false;
        if (!Objects.equals(surname, user.surname)) return false;
        if (!Objects.equals(number, user.number)) return false;
        return Objects.equals(mail, user.mail);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        return result;
    }
}
