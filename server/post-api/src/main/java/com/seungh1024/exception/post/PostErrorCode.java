package com.seungh1024.exception.post;

import com.seungh1024.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/*
 * Post Error Code 정의
 *
 * @Author 강승훈
 * @Since 2023.07.12
 *
 * */
@Getter
@RequiredArgsConstructor
public enum PostErrorCode implements ErrorCode {
    POST_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND,404,"게시글이 존재하지 않습니다."),
    INACTIVE_USER_ERROR(HttpStatus.FORBIDDEN, 403,"권한이 없는 사용자입니다"),
    ;
    private final HttpStatus httpStatus;
    private final int code;
    private final String message;
}
