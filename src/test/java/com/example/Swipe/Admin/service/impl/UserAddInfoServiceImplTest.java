package com.example.Swipe.Admin.service.impl;

import com.example.Swipe.Admin.entity.News;
import com.example.Swipe.Admin.entity.UserAddInfo;
import com.example.Swipe.Admin.enums.TypeNotification;
import com.example.Swipe.Admin.repository.UserAddInfoRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserAddInfoServiceImplTest {
    @Mock
    private UserAddInfoRepo userAddInfoRepo;
    @InjectMocks
    private UserAddInfoServiceImpl userAddInfoService;

    @Test
    void countSub() {
        List<UserAddInfo> newsList = Arrays.asList(
                UserAddInfo.builder().dateSub(LocalDate.of(2023,5,28)).build(),
                UserAddInfo.builder().dateSub(LocalDate.of(2023,5,20)).build(),
                UserAddInfo.builder().dateSub(LocalDate.of(2023,10,13)).build(),
                UserAddInfo.builder().dateSub(LocalDate.of(2023,5,28)).build(),
                UserAddInfo.builder().dateSub(LocalDate.of(2023,1,19)).build(),
                UserAddInfo.builder().dateSub(LocalDate.of(2023,1,19)).build()

        );
        when(userAddInfoRepo.findAll()).thenReturn(newsList);
        int count5 = userAddInfoService.countSub(5);
        int count10 = userAddInfoService.countSub(10);
        int count1 = userAddInfoService.countSub(1);
        assertEquals(3,count5);
        assertEquals(1,count10);
        assertEquals(2,count1);
    }
    @Test
    void findAll() {
        List<UserAddInfo> userAddInfos = Arrays.asList(
                UserAddInfo.builder().build(),
                UserAddInfo.builder().build(),
                UserAddInfo.builder().build(),
                UserAddInfo.builder().build(),
                UserAddInfo.builder().build(),
                UserAddInfo.builder().build(),
                UserAddInfo.builder().build()
        );
        when(userAddInfoRepo.findAll()).thenReturn(userAddInfos);
        List<UserAddInfo> userAddInfoList = userAddInfoService.findAll();
        assertEquals(7,userAddInfoList.size());
    }

    @Test
    void findById() {
        UserAddInfo userAddInfo = UserAddInfo.builder().idUserAddInfo(1).typeNotification(TypeNotification.ME).dateSub(LocalDate.of(2023,4,5)).callSms(true).build();
        when(userAddInfoRepo.findById(1)).thenReturn(Optional.of(userAddInfo));
        UserAddInfo userAddInfo1 = userAddInfoService.findById(1);
        assertEquals(1,userAddInfo1.getIdUserAddInfo());
        assertEquals(TypeNotification.ME, userAddInfo1.getTypeNotification());
        assertEquals(LocalDate.of(2023,4,5),userAddInfo1.getDateSub());
        assertTrue(userAddInfo1.isCallSms());
        UserAddInfo nullUserAddInfo = userAddInfoService.findById(2);
        assertNull(nullUserAddInfo);
    }


    @Test
    void saveEntity() {
        UserAddInfo userAddInfo = UserAddInfo.builder().typeNotification(TypeNotification.ME).dateSub(LocalDate.of(2023,4,5)).callSms(true).build();
        UserAddInfo userAddInfoFake = UserAddInfo.builder().typeNotification(TypeNotification.MEANDAGENT).dateSub(LocalDate.of(2023,4,5)).callSms(true).build();
        userAddInfoService.saveEntity(userAddInfo);
        verify(userAddInfoRepo).save(userAddInfo);
    }

    @Test
    void deleteById() {
        userAddInfoService.deleteById(1);
        verify(userAddInfoRepo,times(1)).deleteById(1);
    }

    @Test
    void updateEntity() {
        UserAddInfo userAddInfo = UserAddInfo.builder().idUserAddInfo(1).typeNotification(TypeNotification.ME).dateSub(LocalDate.of(2023,4,5)).callSms(true).build();
        when(userAddInfoRepo.findById(1)).thenReturn(Optional.of(userAddInfo));
        UserAddInfo userAddInfoUpdate = UserAddInfo.builder().typeNotification(TypeNotification.MEANDAGENT).dateSub(LocalDate.of(2023,5,5)).callSms(true).build();
        userAddInfoService.updateEntity(userAddInfoUpdate, 1);
        UserAddInfo userAddInfoSave = UserAddInfo.builder().idUserAddInfo(1).typeNotification(TypeNotification.MEANDAGENT).dateSub(LocalDate.of(2023,5,5)).callSms(true).build();
        verify(userAddInfoRepo).saveAndFlush(userAddInfoSave);
    }
}