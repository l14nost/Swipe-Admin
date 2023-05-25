package com.example.Swipe.Admin.dto;

import com.example.Swipe.Admin.enums.TypeNotification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAddInfoDTO {
    boolean callSms;
    LocalDate dateSub;
    TypeNotification typeNotification;
}
