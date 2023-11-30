package com.api.users.application;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidatorTest {

    @Test
    public void testEmailIsValid() {
        String pattern = "^[A-Za-z0-9._%+-]+@testdomain\\.com$";
        String email = "test@testdomain.com";

        boolean isValid = Validator.isValidEmail(email, pattern);
        assertEquals(true, isValid);
    }

    @Test
    public void testEmailIsInvalid() {
        String pattern = "^[A-Za-z0-9._%+-]+@testdomain\\.com$";
        String email = "test@google.com";

        boolean isValid = Validator.isValidEmail(email, pattern);
        assertEquals(false, isValid);
    }

    @Test
    public void testPasswordIsValid() {
        String pattern = "^(?!\\s*$)[0-9\\s]{4}$?";
        String password = "1234";

        boolean isValid = Validator.isValidPassword(password, pattern);
        assertEquals(true, isValid);
    }

    @Test
    public void testPasswordIsInvalid() {
        String pattern = "^(?!\\s*$)[0-9\\s]{4}$?";
        String password = "123456";

        boolean isValid = Validator.isValidPassword(password, pattern);
        assertEquals(false, isValid);
    }


}
