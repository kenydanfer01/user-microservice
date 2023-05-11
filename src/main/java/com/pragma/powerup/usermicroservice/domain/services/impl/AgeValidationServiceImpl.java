package com.pragma.powerup.usermicroservice.domain.services.impl;

import com.pragma.powerup.usermicroservice.domain.services.IAgeValidationService;
import com.pragma.powerup.usermicroservice.domain.validations.AgeValidation;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AgeValidationServiceImpl implements IAgeValidationService {

    @Override
    public boolean isAdult(LocalDate birthday) {
        return AgeValidation.isAdult(birthday);    }
}
