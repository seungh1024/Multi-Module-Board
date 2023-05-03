package com.seungh1024.encrypt;

public interface PasswordChecker {
    Boolean isCorrectPassword(String inputPassword, String memberPassword, String salt);
}
