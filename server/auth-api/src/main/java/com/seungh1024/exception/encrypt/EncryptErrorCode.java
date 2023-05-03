package com.seungh1024.exception.encrypt;

import com.seungh1024.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum EncryptErrorCode implements ErrorCode {
    Algorithm_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND,404,"존재하지 않는 암호화 알고리즘입니다."),
    ;
    private final HttpStatus httpStatus;
    private final int code;
    private final String message;
}
