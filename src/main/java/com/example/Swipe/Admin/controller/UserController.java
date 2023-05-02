package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }
}
