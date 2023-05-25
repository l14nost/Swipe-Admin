package com.example.Swipe.Admin.dto;

import com.example.Swipe.Admin.enums.TypeUser;
import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class BlackListDTO {

    int idUser;
    String name;
    String surname;
    String mail;
    TypeUser typeUser;

}
