package com.example.Swipe.Admin.mapper;

import com.example.Swipe.Admin.dto.ClientDTO;

import com.example.Swipe.Admin.entity.User;
import com.example.Swipe.Admin.entity.UserAddInfo;
import com.example.Swipe.Admin.enums.Role;
import com.example.Swipe.Admin.enums.TypeUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.function.Function;
@RequiredArgsConstructor
public class ClientMapper{
    @Value("${upload.path}")
    private String upload;

    public static User toEntity(ClientDTO userDTO) {

        User user = User.builder()
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .typeUser(userDTO.getType())
                .role(Role.USER)
                .mail(userDTO.getMail())
                .blackList(false)
                .number(userDTO.getNumber())
                .build();
        if (userDTO.getUserAddInfoDTO()!=null) {
            UserAddInfo userAddInfo = UserAddInfoMapper.toEntity(userDTO.getUserAddInfoDTO());
            user.setUserAddInfo(userAddInfo);
        }
        if (userDTO.getAgent()!=null){
            user.setAgent(AgentMapper.toEntity(userDTO.getAgent()));
        }
//        if (!userDTO.getFilename().isEmpty()) {
//            File uploadDirGallery = new File(upload);
//            if (!uploadDirGallery.exists()) {
//                uploadDirGallery.mkdir();
//            }
//            String uuid = UUID.randomUUID().toString();
//            String fileNameGallery = uuid + "-" + userDTO.getFilename().getOriginalFilename();
//            String resultNameGallery = upload + "" + fileNameGallery;
//            try {
//                userDTO.getFilename().transferTo(new File((resultNameGallery)));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            user.setFilename("../uploads/" + fileNameGallery);
//        }
//        else if(userDTO.getFilename().isEmpty()&&userDTO.getIdUser()==0){
//            user.setFilename("../admin/dist/img/default.jpg");
//        }
        return user;
    }

    public static ClientDTO apply(User user) {
        ClientDTO clientDTO = ClientDTO.builder()
                .idUser(user.getIdUser())
                .name(user.getName())
                .mail(user.getMail())
                .surname(user.getSurname())
                .photo(user.getFilename())
//                .userAddInfoDTO()
                .type(user.getTypeUser())
                .number(user.getNumber())
                .build();
        if(user.getAgent()!=null){
            clientDTO.setAgent(AgentMapper.apply(user.getAgent()));
        }
        if (user.getUserAddInfo()!=null){
            clientDTO.setUserAddInfoDTO(UserAddInfoMapper.apply(user.getUserAddInfo()));
        }
        return clientDTO;

    }
}
