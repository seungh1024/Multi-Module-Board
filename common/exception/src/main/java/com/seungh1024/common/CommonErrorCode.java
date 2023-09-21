package com.seungh1024.common;

import com.seungh1024.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/*
 * 공통적인 에러 정의 클래스. 입력 형식 등의 에러 정의
 *
 * @Author 강승훈
 * @Since 2023.03.21
 * */
@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {
    INVALID_ARGUMENT_ERROR(HttpStatus.BAD_REQUEST, 400, "올바르지 않은 파라미터입니다."),
    INVALID_FORMAT_ERROR(HttpStatus.BAD_REQUEST,400, "올바르지 않은 포맷입니다."),
    INVALID_TYPE_ERROR(HttpStatus.BAD_REQUEST, 400, "올바르지 않은 타입입니다."),
    ILLEGAL_ARGUMENT_ERROR(HttpStatus.BAD_REQUEST, 400, "필수 파라미터가 없습니다"),
    JSON_PARSE_ERROR(HttpStatus.BAD_REQUEST, 400, "JSON 형식 및 해석 불가능한 문자(/ 등)를 제거및 데이터 타입을 확인하고 보내주세요."),
    UNEXPECTED_TYPE_ERROR(HttpStatus.FORBIDDEN, 400,"알맞은 요청 타입으로 요청해주세요"),
    INVALID_DATA_ERROR(HttpStatus.FORBIDDEN, 400,"요청 데이터를 확인해주세요"),
    INVALID_MEMBER_ERROR(HttpStatus.FORBIDDEN, 403,"권한이 없는 사용자입니다"),
    METHOD_NOT_ALLOWED(HttpStatus.FORBIDDEN, 405,"메소드를 확인해주세요."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "서버 오류가 발생했습니다. 잠시 후 다시 시도해주세요."),
    DATABASE_CONNECTION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "데이터베이스 연결 오류입니다. 잠시 후 다시 시도해주세요.");
    ;
    private final HttpStatus httpStatus;
    private final int code;
    private final String message;
}
