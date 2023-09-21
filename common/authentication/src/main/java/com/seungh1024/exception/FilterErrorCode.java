package com.seungh1024.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/*
 * 에러 정의 클래스
 *
 * @Author 강승훈
 * @Since 2023.03.21
 *
 * */

@Getter
@RequiredArgsConstructor
public enum FilterErrorCode{
    TOKEN_EXPIRED_ERROR(HttpStatus.UNAUTHORIZED,401,"토큰이 만료되었습니다"),
    TOKEN_SIGNATURE_ERROR(HttpStatus.UNAUTHORIZED,401,"유효하지 않은 토큰입니다."),
    TOKEN_NOT_EXIST(HttpStatus.NOT_FOUND,404,"토큰이 존재하지 않습니다."),
    TOKEN_NOT_CORRECT(HttpStatus.NOT_FOUND,401,"올바른 토큰 형식이 아닙니다."),
    DATABASE_CONNECTION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "데이터베이스 연결 오류입니다. 잠시 후 다시 시도해주세요."),
    ;
    private final HttpStatus httpStatus;
    private final int code;
    private final String message;
}
