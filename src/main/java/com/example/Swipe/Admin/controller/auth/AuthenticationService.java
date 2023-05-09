//package com.example.Swipe.Admin.controller.auth;
//
//import com.example.Swipe.Admin.config.JwtService;
//import com.example.Swipe.Admin.entity.Admin;
//import com.example.Swipe.Admin.enums.Role;
//import com.example.Swipe.Admin.repository.AdminRepo;
//import com.example.Swipe.Admin.token.Token;
//import com.example.Swipe.Admin.token.TokenRepo;
//import com.example.Swipe.Admin.token.TokenType;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class AuthenticationService {
//    private final AdminRepo adminRepo;
//    private final TokenRepo tokenRepo;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtService jwtService;
//    private final AuthenticationManager authenticationManager;
//
//    public AuthenticationResponse register(RegisterRequest request) {
//
//        var admin = Admin.builder().login(request.getLogin()).password(passwordEncoder.encode(request.getPassword())).role(Role.ADMIN).build();
//        var savedAdmin = adminRepo.save(admin);
//
//        var jwtToken = jwtService.generateToken(admin);
////        var refreshToken = jwtService.generateRefreshToken(admin);
//        saveAdminToken(savedAdmin, jwtToken);
//        return AuthenticationResponse.builder().token(jwtToken).build();
//    }
//
//    private void saveAdminToken(Admin admin, String jwtToken) {
//        var token = Token.builder()
//                .admin(admin)
//                .token(jwtToken)
//                .tokenType(TokenType.BEARER)
//                .revoked(false)
//                .expired(false)
//                .build();
//        tokenRepo.save(token);
//    }
//
//
//
//    public AuthenticationResponse authenticate(AuthenticationRequest request) {
//
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getLogin(),
//                        request.getPassword()
//                )
//        );
//
//        var admin = adminRepo.findByLogin(request.getLogin()).orElseThrow();
//        var jwtToken = jwtService.generateToken(admin);
//        revokeAllAdminTokens(admin);
//        saveAdminToken(admin,jwtToken);
//        return AuthenticationResponse.builder().token(jwtToken).build();
//    }
//
//    private void revokeAllAdminTokens(Admin admin){
//        var validAdminToken = tokenRepo.findAllValidTokensByAdmin(admin.getIdAdmin());
//        if(validAdminToken.isEmpty()){
//            return;
//        }
//        validAdminToken.forEach(t -> {
//            t.setExpired(true);
//            t.setRevoked(true);
//        });
//        tokenRepo.saveAll(validAdminToken);
//    }
//
//
//}
