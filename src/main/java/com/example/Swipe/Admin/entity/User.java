package com.example.Swipe.Admin.entity;

import com.example.Swipe.Admin.enums.Role;
import com.example.Swipe.Admin.enums.TypeNotification;
import com.example.Swipe.Admin.enums.TypeUser;
import jakarta.persistence.*;
import lombok.*;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser")
    private int idUser;
    @NotBlank(message = "Input name")
    private String name;

    private String password;
    @NotBlank(message = "Input surname")
    @Size(min = 3, max = 20, message = "3 to 20")
    private String surname;

    private String number;

    @Email(message = "email is not correct")
    @NotBlank(message = "Input email")
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
}
