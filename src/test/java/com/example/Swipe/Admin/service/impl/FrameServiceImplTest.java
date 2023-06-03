package com.example.Swipe.Admin.service.impl;

import com.example.Swipe.Admin.entity.Frame;
import com.example.Swipe.Admin.entity.LCD;
import com.example.Swipe.Admin.repository.FrameRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FrameServiceImplTest {
    @Mock
    private FrameRepo frameRepo;
    @InjectMocks
    private FrameServiceImpl frameService;
    @Test
    void findAll() {
        List<Frame> frames = new ArrayList<>();
        for (int i = 0 ;i<4;i++){
            frames.add(Frame.builder().build());
        }
        when(frameRepo.findAll()).thenReturn(frames);
        List<Frame> framesCheck = frameService.findAll();
        assertEquals(4,framesCheck.size());
    }

    @Test
    void findById() {
        Frame frame = Frame.builder()
                .idFrame(3)
                .num(10)
                .lcd(LCD.builder().idLcd(4).build()).build();
        Frame frameTest = Frame.builder()
                .idFrame(3)
                .num(10)
                .lcd(LCD.builder().idLcd(4).build()).build();
        when(frameRepo.findById(3)).thenReturn(Optional.of(frame));
        Frame frameCheck = frameService.findById(3);
        assertEquals(frameTest,frameCheck);
    }
    @Test
    void findByIdEmpty() {

        when(frameRepo.findById(1)).thenReturn(Optional.empty());
        Frame frameCheck = frameService.findById(1);
        assertNull(frameCheck);
    }

    @Test
    void saveEntity() {
        Frame frame = Frame.builder()
                .num(10)
                .lcd(LCD.builder()
                        .idLcd(4)
                        .build())
                .build();
        Frame frameFake = Frame.builder()
                .num(0)
                .lcd(LCD.builder()
                        .idLcd(4)
                        .build())
                .build();
        frameService.saveEntity(frame);
        verify(frameRepo).save(frame);
    }

    @Test
    void deleteById() {
        frameService.deleteById(1);
        verify(frameRepo).deleteById(1);
    }

    @Test
    void updateEntity() {
        Frame frame =Frame.builder()
                .idFrame(10)
                .num(10)
                .lcd(LCD.builder().build())
                .apartmentList(new ArrayList<>())
                .build();
        when(frameRepo.findById(10)).thenReturn(Optional.of(frame));
        Frame frameUpdate = Frame.builder()
                .num(9)
                .lcd(LCD.builder().build())
                .apartmentList(new ArrayList<>())
                .build();
        frameService.updateEntity(frameUpdate,10);
        Frame frameSave = Frame.builder()
                .idFrame(10)
                .num(9)
                .lcd(LCD.builder().build())
                .apartmentList(new ArrayList<>())
                .build();
        verify(frameRepo).saveAndFlush(frameSave);
    }
}