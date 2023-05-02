package com.example.Swipe.Admin.service.impl;

import com.example.Swipe.Admin.entity.Contractor;
import com.example.Swipe.Admin.entity.User;
import com.example.Swipe.Admin.repository.UserRepo;
import com.example.Swipe.Admin.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> users(){
        return userRepo.findAll();
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User findById(int id) {
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        else {
            return User.builder().build();
        }
    }

    @Override
    public void saveEntity(User user) {
        userRepo.save(user);
    }

    @Override
    public void deleteById(int id) {
        userRepo.deleteById(id);
    }

    @Override
    public void updateEntity(User user, int id) {

    }
}
