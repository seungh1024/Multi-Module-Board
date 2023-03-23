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
    MEMBER_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND,404,"존재하지 않는 사용자입니다"),
    MEMBER_ALREADY_EXISTS_ERROR(HttpStatus.CONFLICT,409, "는 이미 존재하는 회원입니다"),
    INACTIVE_USER_ERROR(HttpStatus.FORBIDDEN, 403,"권한이 없는 사용자입니다"),
    INVALID_PASSWORD_ERROR(HttpStatus.BAD_REQUEST,400,"비밀번호가 일치하지 않습니다."),
    ACCOUNT_EXPIRED_ERROR(HttpStatus.UNAUTHORIZED, 401, "로그아웃 되었습니다. 다시 로그인 해주세요."),
    ;
    private final HttpStatus httpStatus;
    private final int code;
    private final String message;
}
