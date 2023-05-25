package com.example.Swipe.Admin.mapper;

import com.example.Swipe.Admin.dto.ClientDTO;
import com.example.Swipe.Admin.dto.UserAddInfoDTO;
import com.example.Swipe.Admin.entity.User;
import com.example.Swipe.Admin.entity.UserAddInfo;
import com.example.Swipe.Admin.enums.Role;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserAddInfoMapper implements Function<UserAddInfo,UserAddInfoDTO> {
    public UserAddInfo toEntity(UserAddInfoDTO userAddInfoDTO){
       return UserAddInfo.builder()
               .callSms(userAddInfoDTO.isCallSms())
               .typeNotification(userAddInfoDTO.getTypeNotification())
               .dateSub(userAddInfoDTO.getDateSub())
               .typeNotification(userAddInfoDTO.getTypeNotification())
               .build();
    }

    @Override
    public UserAddInfoDTO apply(UserAddInfo userAddInfo) {
        return UserAddInfoDTO.builder()
                .callSms(userAddInfo.isCallSms())
                .typeNotification(userAddInfo.getTypeNotification())
                .dateSub(userAddInfo.getDateSub())
                .build();

    }
}
