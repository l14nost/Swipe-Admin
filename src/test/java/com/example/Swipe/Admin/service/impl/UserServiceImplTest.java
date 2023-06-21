package com.example.Swipe.Admin.service.impl;

import com.example.Swipe.Admin.dto.BlackListDTO;
import com.example.Swipe.Admin.dto.ClientDTO;
import com.example.Swipe.Admin.entity.User;
import com.example.Swipe.Admin.enums.Role;
import com.example.Swipe.Admin.enums.TypeUser;
import com.example.Swipe.Admin.repository.UserRepo;
import com.example.Swipe.Admin.specification.BlackListSpecification;
import com.example.Swipe.Admin.specification.UserSpecification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepo userRepo;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void findAllByType() {
        List<User> notary = Arrays.asList(
                User.builder().typeUser(TypeUser.NOTARY).build(),
                User.builder().typeUser(TypeUser.NOTARY).build(),
                User.builder().typeUser(TypeUser.NOTARY).build(),
                User.builder().typeUser(TypeUser.NOTARY).build(),
                User.builder().typeUser(TypeUser.NOTARY).build()
        );
        when(userRepo.findAllByTypeUserAndBlackListIsFalse(TypeUser.NOTARY)).thenReturn(notary);
        List<User> users = userService.findAllByType(TypeUser.NOTARY);
        assertEquals(5,users.size());
        for (User user : users) {
            assertEquals(TypeUser.NOTARY, user.getTypeUser());
        }

    }

    @Test
    void findAllByTypeDTO() {
        List<User> notary = Arrays.asList(
                User.builder().typeUser(TypeUser.NOTARY).build(),
                User.builder().typeUser(TypeUser.NOTARY).build(),
                User.builder().typeUser(TypeUser.NOTARY).build(),
                User.builder().typeUser(TypeUser.NOTARY).build(),
                User.builder().typeUser(TypeUser.NOTARY).build()
        );
        when(userRepo.findAllByTypeUserAndBlackListIsFalse(TypeUser.NOTARY)).thenReturn(notary);
        List<ClientDTO> users = userService.findAllByTypeDTO(TypeUser.NOTARY);
        assertEquals(5,users.size());
        for (ClientDTO user : users) {
            assertEquals(TypeUser.NOTARY, user.getType());
        }
    }

    @Test
    void findAllByTypePagination() {
        List<User> client = Arrays.asList(
                User.builder().name("Amir").typeUser(TypeUser.CLIENT).build(),
                User.builder().typeUser(TypeUser.CLIENT).build(),
                User.builder().typeUser(TypeUser.CLIENT).build(),
                User.builder().typeUser(TypeUser.CLIENT).build(),
                User.builder().typeUser(TypeUser.CLIENT).build()
        );
        Pageable pageable = PageRequest.of(0,3);
        UserSpecification blackListSpecification1 = UserSpecification.builder().typeUser(TypeUser.CLIENT).sort("idUser").build();
        when(userRepo.findAll(blackListSpecification1,pageable)).thenReturn(new PageImpl<>(client));
        Page<ClientDTO> users = userService.findAllByTypePagination(TypeUser.CLIENT,pageable,"null","idUser");
        assertEquals(5,users.getContent().size());
        for (ClientDTO user : users) {
            assertEquals(TypeUser.CLIENT, user.getType());
        }
        List<User> client1 = Arrays.asList(
                User.builder().name("Amir").typeUser(TypeUser.CLIENT).build()
        );
        UserSpecification blackListSpecification = UserSpecification.builder().keyWord("Amir").typeUser(TypeUser.CLIENT).sort("idUser").build();
        when(userRepo.findAll(blackListSpecification,pageable)).thenReturn(new PageImpl<>(client1));
        Page<ClientDTO> users1 = userService.findAllByTypePagination(TypeUser.CLIENT,pageable,"Amir","idUser");
        assertEquals(1,users1.getContent().size());
    }

    @Test
    void blackList() {
        List<User> client = Arrays.asList(
                User.builder().typeUser(TypeUser.CLIENT).build(),
                User.builder().typeUser(TypeUser.CLIENT).build(),
                User.builder().typeUser(TypeUser.CLIENT).build(),
                User.builder().typeUser(TypeUser.CLIENT).build(),
                User.builder().typeUser(TypeUser.CLIENT).build()
        );
        Pageable pageable = PageRequest.of(0,3);
        BlackListSpecification blackListSpecification = BlackListSpecification.builder().keyWord("null").sortedBy("surname").build();
        when(userRepo.findAll(blackListSpecification,pageable)).thenReturn(new PageImpl<>(client));
        Page<BlackListDTO> users = userService.blackList(pageable,"null", "surname");
        assertEquals(5,users.getContent().size());
    }

    @Test
    void findAll() {
        List<User> users = Arrays.asList(
                User.builder().typeUser(TypeUser.CLIENT).build(),
                User.builder().typeUser(TypeUser.NOTARY).build(),
                User.builder().typeUser(TypeUser.CLIENT).build(),
                User.builder().typeUser(TypeUser.CONTRACTOR).build(),
                User.builder().typeUser(TypeUser.CLIENT).build()
        );
        when(userRepo.findAll()).thenReturn(users);
        List<User> usersList = userService.findAll();
        assertEquals(5,usersList.size());
    }

    @Test
    void findByIdDTO() {
        User user = User.builder()
                .idUser(1)
                .number("123123123")
                .mail("123@gmail.com")
                .name("User")
                .surname("Surname")
                .blackList(false)
                .role(Role.USER)
                .build();
        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        ClientDTO clientDTO = userService.findByIdDTO(1);
        assertEquals(1,clientDTO.getIdUser());
        assertEquals("123123123",clientDTO.getNumber());
        assertEquals("123@gmail.com",clientDTO.getMail());
        assertEquals("User",clientDTO.getName());
        assertEquals("Surname",clientDTO.getSurname());
        ClientDTO clientNull = userService.findByIdDTO(2);
        assertNull(clientNull);
    }

    @Test
    void findById() {
        User user = User.builder()
                .idUser(1)
                .number("123123123")
                .mail("123@gmail.com")
                .name("User")
                .surname("Surname")
                .blackList(false)
                .role(Role.USER)
                .build();
        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        User clientDTO = userService.findById(1);
        assertEquals(1,clientDTO.getIdUser());
        assertEquals("123123123",clientDTO.getNumber());
        assertEquals("123@gmail.com",clientDTO.getMail());
        assertEquals("User",clientDTO.getName());
        assertEquals("Surname",clientDTO.getSurname());
        User clientNull = userService.findById(2);
        assertNull(clientNull);
    }

    @Test
    void saveDTO() {
        ClientDTO user = ClientDTO.builder()
                .idUser(0)
                .number("123123123")
                .mail("123@gmail.com")
                .name("User")
                .surname("Surname")
//                .filename(new MockMultipartFile("file","example.txt","text/plain","Hello World".getBytes()))
                .build();
        userService.setUpload("/C:/Users/Amir Banov/IdeaProjects/Swipe-Admin/uploads/");
        userService.saveEntityDTO(user);
        User userSave = User.builder()
                .idUser(1)
                .number("123123123")
                .mail("123@gmail.com")
                .name("User")
                .surname("Surname")
                .blackList(false)
                .role(Role.USER)
                .build();
        verify(userRepo).save(userSave);
    }

    @Test
    void saveDTO_emptyFile() {
        ClientDTO user ;
        user = ClientDTO.builder()
                .idUser(0)
                .number("123123123")
                .mail("123@gmail.com")
                .name("User")
                .surname("Surname")
//                    .filename(new MockMultipartFile("file","example.txt","text/plain",new ByteArrayResource(new byte[0]).getInputStream()))
                .build();
        userService.setUpload("/C:/Users/Amir Banov/IdeaProjects/Swipe-Admin/uploads/");
        userService.saveEntityDTO(user);
        User userSave = User.builder()
                .idUser(1)
                .number("123123123")
                .mail("123@gmail.com")
                .name("User")
                .surname("Surname")
                .blackList(false)
                .role(Role.USER)
                .build();
        verify(userRepo).save(userSave);
    }

    @Test
    void saveEntity() {
        User user = User.builder()
                .number("123123123")
                .mail("123@gmail.com")
                .name("User")
                .surname("Surname")
                .build();
        userService.saveEntity(user);
        User userSave = User.builder()
                .number("123123123")
                .mail("123@gmail.com")
                .name("User")
                .surname("Surname")
                .blackList(false)
                .role(Role.USER)
                .build();
        verify(userRepo).save(userSave);
    }
//804059ae-eba1-49a0-8e06-ccf3f6b545fa-stock2.jpg создать файл
    @Test
    void deleteById() {
        User user = User.builder()
                .number("123123123")
                .mail("123@gmail.com")
                .name("User")
                .surname("Surname")
                .filename("../uploads/804059ae-eba1-49a0-8e06-ccf3f6b545fa-stock2.jpg")
                .build();
        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        userService.setUpload("/C:/Users/Amir Banov/IdeaProjects/Swipe-Admin/uploads/");
        userService.deleteById(1);

        verify(userRepo).deleteById(1);
    }

    @Test
    void updateEntity() {
        User user = User.builder()
                .idUser(1)
                .number("123123123")
                .mail("123@gmail.com")
                .name("User")
                .surname("Surname")
                .filename("../uploads/804059ae-eba1-49a0-8e06-ccf3f6b545fa-stock2.jpg")
                .build();
        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        User userUpdate = User.builder()
                .number("123123123")
                .mail("124@gmail.com")
                .name("User1")
                .surname("Surname1")
                .filename("../uploads/804059ae-eba1-49a0-8e06-ccf3f6b545fa-stock3.jpg")
                .build();
        userService.updateEntity(userUpdate,1);
        User userSave = User.builder()
                .idUser(1)
                .number("123123123")
                .mail("124@gmail.com")
                .name("User1")
                .surname("Surname1")
                .filename("../uploads/804059ae-eba1-49a0-8e06-ccf3f6b545fa-stock3.jpg")
                .build();
        verify(userRepo).saveAndFlush(userSave);
    }

    @Test
    void updateDTO() {
        User user = User.builder()
                .idUser(1)
                .number("123123123")
                .mail("123@gmail.com")
                .name("User")
                .surname("Surname")
                .filename("../uploads/804059ae-eba1-49a0-8e06-ccf3f6b545fa-stock2.jpg")
                .build();
        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        ClientDTO userUpdate = ClientDTO.builder()
                .number("123123123")
                .mail("124@gmail.com")
                .name("User1")
                .surname("Surname1")
//                .filename(new MockMultipartFile("file","example.txt","text/plain","Hello World".getBytes()))
                .build();
        userService.setUpload("/C:/Users/Amir Banov/IdeaProjects/Swipe-Admin/uploads/");
        userService.updateDTO(userUpdate,1);
        User userSave = User.builder()
                .idUser(1)
                .number("123123123")
                .mail("124@gmail.com")
                .name("User1")
                .surname("Surname1")
                .filename("../uploads/example.txt")
                .build();

        verify(userRepo).saveAndFlush(userSave);
    }
}