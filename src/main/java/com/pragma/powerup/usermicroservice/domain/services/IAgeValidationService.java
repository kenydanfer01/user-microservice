package com.pragma.powerup.usermicroservice.domain.services;

import java.time.LocalDate;

public interface IAgeValidationService {
    boolean isAdult(LocalDate birthday);
}
