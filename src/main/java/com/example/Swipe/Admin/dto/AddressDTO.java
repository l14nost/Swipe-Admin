package com.example.Swipe.Admin.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDTO {
    String area;
    String city;
    String street;
    String number;
}
