package com.pragma.powerup.usermicroservice.domain.validations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

class AgeValidationTest {
    @Test
    public void testIsAdultWith18Years() {
        LocalDate birthday = LocalDate.now().minusYears(18);
        boolean result = AgeValidation.isAdult(birthday);
        Assertions.assertTrue(result);
    }

    @Test
    public void testIsAdultWith17Years() {
        LocalDate birthday = LocalDate.now().minusYears(17);
        boolean result = AgeValidation.isAdult(birthday);
        Assertions.assertFalse(result);
    }

    @Test
    public void testIsAdultWithNullBirthday() {
        boolean result = AgeValidation.isAdult(null);
        Assertions.assertFalse(result);
    }

}