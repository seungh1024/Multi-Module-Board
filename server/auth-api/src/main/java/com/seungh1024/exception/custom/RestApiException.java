package com.seungh1024.exception.custom;

import com.seungh1024.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/*
 * custom rest api exception
 *
 * @Author 강승훈
 * @Since 2023.03.20
 *
 * */

@Getter
@RequiredArgsConstructor
public class RestApiException extends RuntimeException{
    private final ErrorCode errorCode;
}
