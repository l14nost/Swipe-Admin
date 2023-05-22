package com.example.Swipe.Admin.dto;

import com.example.Swipe.Admin.enums.TypeNotification;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserAddInfoDTO {
    boolean callSms;
    LocalDate dateSub;
    TypeNotification typeNotification;
}
