package com.example.Swipe.Admin.dto;

import com.example.Swipe.Admin.enums.TypeUser;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class ClientDTO {

//    int idUser;

    @NotNull(message = "Name must be not null")
    @NotEmpty(message = "Name must be not null")
    String name;

    @NotNull(message = "Surname must be not null")
    @NotEmpty(message = "Surname must be not null")
    String surname;

//    @NotNull(message = "Mail must be not null")
    @NotEmpty(message = "Mail must be not null")
    @Email(regexp = "^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@"
            + "[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$")
    String mail;

    boolean blackList;

    String filename;
    String number;

//    TypeUser typeUser;

    UserAddInfoDTO userAddInfoDTO;
    AgentDTO agentDTO;

}
