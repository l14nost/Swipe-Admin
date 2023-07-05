package com.example.Swipe.Admin.dto;

import com.example.Swipe.Admin.enums.TypeNotification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAddInfoDTO {
    boolean callSms;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Не должно быть пустым")
    LocalDate dateSub;
    TypeNotification typeNotification;
}
