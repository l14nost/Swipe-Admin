package com.example.Swipe.Admin.controller.auth;

import com.example.Swipe.Admin.config.JwtService;
import com.example.Swipe.Admin.entity.Admin;
import com.example.Swipe.Admin.enums.Role;
import com.example.Swipe.Admin.repository.AdminRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AdminRepo adminRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        var admin = Admin.builder().login(request.getLogin()).password(passwordEncoder.encode(request.getPassword())).role(Role.ADMIN).build();
        var saveAdmin = adminRepo.save(admin);

        var jwtToken = jwtService.generateToken(admin);
        var refreshToken = jwtService.generateRefreshToken(admin);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );

        var admin = adminRepo.findByLogin(request.getLogin()).orElseThrow();

        var jwtToken = jwtService.generateToken(admin);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }


}
