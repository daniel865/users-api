package com.api.users.application;

import java.util.regex.Pattern;

public class Validator {
    public static boolean isValidEmail(String email, String pattern) {
        return isValidText(email, pattern);
    }

    public static boolean isValidPassword(String password, String pattern) {
        return isValidText(password, pattern);
    }

    private static boolean isValidText(String text, String pattern) {
        return Pattern.compile(pattern)
                .matcher(text)
                .matches();
    }
}
