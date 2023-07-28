package com.seungh1024.encrypt.dto;

import lombok.Getter;

@Getter
public class PasswordCheckerDto {
    private final String inputPassword;
    private final String memberPassword;
    private final String salt;

    public PasswordCheckerDto(String inputPassword, String memberPassword, String salt){
        this.inputPassword = inputPassword;
        this.memberPassword = memberPassword;
        this.salt = salt;
    }
}
