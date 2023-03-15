package com.seungh1024.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    String name();
    HttpStatus getHttpStatus();
    int getCode();
    String getMessage();
}
