package com.example.Swipe.Admin.validation;

import com.example.Swipe.Admin.repository.AgentRepo;
import com.example.Swipe.Admin.repository.UserRepo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueEmailValidatorAgent implements ConstraintValidator<UniqueEmailAgent, String> {
    private final AgentRepo agentRepo;


    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null) {
            return true;
        }

        return agentRepo.countByMail(email)<=1;
    }
}
