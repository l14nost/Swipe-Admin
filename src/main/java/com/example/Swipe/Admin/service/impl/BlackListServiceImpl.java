//package com.example.Swipe.Admin.service.impl;
//
//import com.example.Swipe.Admin.entity.Address;
//import com.example.Swipe.Admin.entity.BlackList;
//import com.example.Swipe.Admin.repository.AddressRepo;
//import com.example.Swipe.Admin.repository.BlackListRepo;
//import com.example.Swipe.Admin.service.AddressService;
//import com.example.Swipe.Admin.service.BlackListService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class BlackListServiceImpl implements BlackListService {
//    private final BlackListRepo blackListRepo;
//
//    @Override
//    public List<BlackList> findAll() {
//        return blackListRepo.findAll();
//    }
//
//    @Override
//    public BlackList findById(int id) {
//        Optional<BlackList> blackList = blackListRepo.findById(id);
//        if(blackList.isPresent()){
//            return blackList.get();
//        }
//        else {
//            return BlackList.builder().build();
//        }
//    }
//
//    @Override
//    public void saveEntity(BlackList blackList) {
//        blackListRepo.save(blackList);
//    }
//
//    @Override
//    public void deleteById(int id) {
//        blackListRepo.deleteById(id);
//    }
//
//    @Override
//    public void updateEntity(BlackList blackList, int id) {
//
//    }
//
//
//
//}
