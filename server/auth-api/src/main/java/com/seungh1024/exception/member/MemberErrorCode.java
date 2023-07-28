package com.seungh1024.exception.member;

import com.seungh1024.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/*
 * Member Error Code 정의
 *
 * @Author 강승훈
 * @Since 2023.03.20
 *
 * */

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements ErrorCode {
    INVALID_PASSWORD_ERROR(HttpStatus.BAD_REQUEST,400,"비밀번호가 일치하지 않습니다."),
    INVALID_ACCESS_ERROR(HttpStatus.BAD_REQUEST,400,"올바르지 않은 접근 경로입니다. 다시 로그인 해주세요."),
    TOKEN_EXPIRED_ERROR(HttpStatus.UNAUTHORIZED, 401, "토큰이 만료되어 로그아웃 되었습니다. 다시 로그인 해주세요."),
    INACTIVE_USER_ERROR(HttpStatus.FORBIDDEN, 403,"권한이 없는 사용자입니다"),
    MEMBER_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND,404,"존재하지 않는 사용자입니다"),
    MEMBER_ALREADY_EXISTS_ERROR(HttpStatus.CONFLICT,409, "해당 아이디는 이미 존재합니다. 다른 아이디를 사용해주세요."),
    ;
    private final HttpStatus httpStatus;
    private final int code;
    private final String message;
}
