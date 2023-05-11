package com.pragma.powerup.usermicroservice.domain.validations;

import java.time.LocalDate;

public class AgeValidation {

    public static boolean isAdult(LocalDate birthday) {
        if (birthday == null) {
            return false;
        }
        LocalDate today = LocalDate.now();
        int age = today.getYear() - birthday.getYear();
        if (birthday.plusYears(age).isAfter(today)) {
            age--;
        }
        return age >= 18;
    }
}
