package com.seungh1024.encrypt;

/*
 * 암호화 방지를 위한 랜덤 문자열 생성 인터페이스
 *
 *  @Author 강승훈
 *  @Since 2023.06.07
 *
 * */

public interface RandomSalt {
    String getSalt();
}
