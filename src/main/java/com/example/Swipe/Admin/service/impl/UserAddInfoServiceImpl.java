package com.example.Swipe.Admin.service.impl;

//import com.example.Swipe.Admin.entity.SalesDepartment;
import com.example.Swipe.Admin.entity.News;
import com.example.Swipe.Admin.entity.User;
import com.example.Swipe.Admin.entity.UserAddInfo;
//import com.example.Swipe.Admin.repository.SalesDepartmentRepo;
import com.example.Swipe.Admin.repository.UserAddInfoRepo;
import com.example.Swipe.Admin.service.UserAddInfoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAddInfoServiceImpl implements UserAddInfoService {
    private Logger log = LoggerFactory.getLogger(UserAddInfoServiceImpl.class);
    private final UserAddInfoRepo userAddInfoRepo;

    public int countSub(int monthNum){
        int count = 0;
        List<UserAddInfo> userAddInfos = userAddInfoRepo.findAll();
        for (UserAddInfo userAddInfo : userAddInfos
        ) {
            if(userAddInfo.getDateSub().getMonthValue() == monthNum){
                count++;
            }
        }
        return count;
    }
    @Override
    public List<UserAddInfo> findAll() {
        return userAddInfoRepo.findAll();
    }

    @Override
    public UserAddInfo findById(int id) {
        return userAddInfoRepo.findById(id).orElse(null);
    }

    @Override
    public void saveEntity(UserAddInfo userAddInfo) {
        userAddInfoRepo.save(userAddInfo);
    }

    @Override
    public void deleteById(int id) {
        userAddInfoRepo.deleteById(id);
    }

    @Override
    public void updateEntity(UserAddInfo userAddInfo, int id) {
        Optional<UserAddInfo> userOptional = userAddInfoRepo.findById(id);
        if (userOptional.isPresent()) {
            UserAddInfo updateUser = userOptional.get();
            if (userAddInfo.getDateSub() != null) {
                updateUser.setDateSub(userAddInfo.getDateSub());
            }
            if (userAddInfo.getTypeNotification() != null) {
                updateUser.setTypeNotification(userAddInfo.getTypeNotification());
            }
            updateUser.setCallSms(userAddInfo.isCallSms());
            userAddInfoRepo.saveAndFlush(updateUser);
        }
    }
}
