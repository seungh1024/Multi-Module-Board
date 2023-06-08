package com.seungh1024.encrypt;

/*
 * 비밀번호 암호화 관련 인터페이스
 *
 *  @Author 강승훈
 *  @Since 2023.06.07
 *
 * */

public interface SeunghPasswordEncoder {
    String encryptPassword(String password, String salt);
}
