package com.seungh1024;

/*
 * 에러 정의 인터페이스
 *
 * @Author 강승훈
 * @Since 2023.03.21
 * */

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    String name();
    HttpStatus getHttpStatus();
    int getCode();
    String getMessage();

}
