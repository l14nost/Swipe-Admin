package com.example.Swipe.Admin.dto;

import com.example.Swipe.Admin.enums.TypeAgent;
import com.example.Swipe.Admin.enums.TypeNotification;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AgentDTO {
    String mail;
    String name;
    String number;
    String surname;
    TypeAgent typeAgent;
}
