package com.example.Swipe.Admin.service.impl;

import com.example.Swipe.Admin.entity.Admin;
import com.example.Swipe.Admin.repository.AdminRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AdminDetailsService implements UserDetailsService {
    private final AdminRepo adminRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> admin = adminRepo.findByLogin(username);
        if (admin.isPresent()){
            Admin authAdmin = Admin.builder().login(admin.get().getLogin()).password(admin.get().getPassword()).role(admin.get().getRole()).build();
            return authAdmin;
        }
        else {
            throw new UsernameNotFoundException("Invalid login or pass");
        }

    }
}
