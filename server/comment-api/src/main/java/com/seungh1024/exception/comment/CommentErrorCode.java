package com.seungh1024.exception.comment;

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
public enum CommentErrorCode implements ErrorCode {
    COMMENT_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND,404,"해당 게시글의 댓글이 존재하지 않습니다."),
    ;
    private final HttpStatus httpStatus;
    private final int code;
    private final String message;
}
