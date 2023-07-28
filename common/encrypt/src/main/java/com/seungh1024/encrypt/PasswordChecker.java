package com.seungh1024.encrypt;

import com.seungh1024.encrypt.dto.PasswordCheckerDto;

/*
 * PasswordChecker 비밀번호 확인 인터페이스
 *
 *  @Author 강승훈
 *  @Since 2023.06.07
 *
 * */
public interface PasswordChecker {
    Boolean isCorrectPassword(PasswordCheckerDto passwordCheckerDto);
}
