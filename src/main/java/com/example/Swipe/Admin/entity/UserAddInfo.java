package com.example.Swipe.Admin.entity;

import com.example.Swipe.Admin.enums.TypeNotification;
import javax.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAddInfo that = (UserAddInfo) o;

        if (idUserAddInfo != that.idUserAddInfo) return false;
        if (callSms != that.callSms) return false;
        if (typeNotification != that.typeNotification) return false;
        return Objects.equals(dateSub, that.dateSub);
    }

    @Override
    public int hashCode() {
        int result = idUserAddInfo;
        result = 31 * result + (typeNotification != null ? typeNotification.hashCode() : 0);
        result = 31 * result + (dateSub != null ? dateSub.hashCode() : 0);
        result = 31 * result + (callSms ? 1 : 0);
        return result;
    }
}
