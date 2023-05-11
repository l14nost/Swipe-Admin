package com.example.Swipe.Admin.service.impl;

//import com.example.Swipe.Admin.entity.SalesDepartment;
import com.example.Swipe.Admin.entity.User;
import com.example.Swipe.Admin.entity.UserAddInfo;
//import com.example.Swipe.Admin.repository.SalesDepartmentRepo;
import com.example.Swipe.Admin.repository.UserAddInfoRepo;
import com.example.Swipe.Admin.service.UserAddInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAddInfoServiceImpl implements UserAddInfoService {
    private final UserAddInfoRepo userAddInfoRepo;


    @Override
    public List<UserAddInfo> findAll() {
        return null;
    }

    @Override
    public UserAddInfo findById(int id) {
        return null;
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
