package com.example.Swipe.Admin.validation;

import com.example.Swipe.Admin.entity.User;
import com.example.Swipe.Admin.repository.UserRepo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    private final UserRepo userRepo;
    private Integer idUser;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
//        if (email == null) {
//            return true;
//        }
//        if (userRepo.countByMail(email)<=1 && idUser!=null){
//            Optional<User> user = userRepo.findByMail(email);
//            if (user.isPresent()) {
//                int id = user.get().getIdUser();
//                if (idUser == id){
//                    return true;
//                }
//                else return false;
//            }
//            else return true;
//
//        }
//        else return false;
        if (email == null) {
            return true;
        }

        return userRepo.countByMail(email)<=1;
    }
}
