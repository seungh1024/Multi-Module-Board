package com.seungh1024.exception;

import com.seungh1024.ErrorCode;
import com.seungh1024.ErrorResponse;
import com.seungh1024.common.CommonErrorCode;
import com.seungh1024.custom.InvalidMemberException;
import com.seungh1024.exception.comment.CommentErrorCode;
import com.seungh1024.exception.custom.CommentNotFoundException;
import com.seungh1024.exception.custom.RestApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

/*
 * 예외 처리 핸들러
 *
 * @Author 강승훈
 * @Since 2023.07.14
 *
 * */
@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e){
        e.printStackTrace();
        log.error("[Exception] cause: {} , message: {}", NestedExceptionUtils.getMostSpecificCause(e), e.getMessage());
        ErrorCode errorCode = CommonErrorCode.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse = ErrorResponse.of(errorCode.getHttpStatus(), errorCode.getCode(), errorCode.getMessage());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(errorResponse);
    }

    @ExceptionHandler(RestApiException.class)
    public ResponseEntity handleSystemException(RestApiException e){
        log.error("[SystemException] cause: {}, message: {}",NestedExceptionUtils.getMostSpecificCause(e),e.getMessage());
        ErrorCode errorCode = e.getErrorCode();
        ErrorResponse errorResponse = ErrorResponse.of(errorCode.getHttpStatus(),errorCode.getCode(),  errorCode.getMessage());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(errorResponse);
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity handleInvalidMemberException(InvalidDataAccessApiUsageException e){
        log.error("[MethodArgumentNotValidException] cause: {}, message: {}",NestedExceptionUtils.getMostSpecificCause(e),e.getMessage());
        ErrorCode errorCode = CommonErrorCode.INVALID_DATA_ERROR;
        ErrorResponse errorResponse = ErrorResponse.of(errorCode.getHttpStatus(),
                errorCode.getCode(),
                errorCode.getMessage());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(errorResponse);
    }
    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity handleCommentNotFoundException(CommentNotFoundException e){
        log.error("[MethodArgumentNotValidException] cause: {}, message: {}",NestedExceptionUtils.getMostSpecificCause(e),e.getMessage());
        ErrorCode errorCode = CommentErrorCode.COMMENT_NOT_FOUND_ERROR;
        ErrorResponse errorResponse = ErrorResponse.of(errorCode.getHttpStatus(),
                errorCode.getCode(),
                errorCode.getMessage());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(errorResponse);
    }

    @ExceptionHandler(InvalidMemberException.class)
    public ResponseEntity handleNullPointerException(InvalidMemberException e){
        log.error("[MethodArgumentNotValidException] cause: {}, message: {}",NestedExceptionUtils.getMostSpecificCause(e),e.getMessage());
        ErrorCode errorCode = CommonErrorCode.INVALID_MEMBER_ERROR;
        ErrorResponse errorResponse = ErrorResponse.of(errorCode.getHttpStatus(),
                errorCode.getCode(),
                errorCode.getMessage());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(errorResponse);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity handleNullPointerException(NoSuchElementException e){
        log.error("[MethodArgumentNotValidException] cause: {}, message: {}",NestedExceptionUtils.getMostSpecificCause(e),e.getMessage());
        ErrorCode errorCode = CommentErrorCode.COMMENT_NOT_FOUND_ERROR;
        ErrorResponse errorResponse = ErrorResponse.of(errorCode.getHttpStatus(),
                errorCode.getCode(),
                errorCode.getMessage());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity handleSystemException(HttpMessageNotReadableException e){
        log.error("[SystemException] cause: {}, message: {}",NestedExceptionUtils.getMostSpecificCause(e),e.getMessage());
        ErrorCode errorCode = CommonErrorCode.JSON_PARSE_ERROR;
        ErrorResponse errorResponse = ErrorResponse.of(errorCode.getHttpStatus(),errorCode.getCode(),  errorCode.getMessage());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.error("[MethodArgumentNotValidException] cause: {}, message: {}",NestedExceptionUtils.getMostSpecificCause(e),e.getMessage());
        ErrorCode errorCode = CommonErrorCode.INVALID_ARGUMENT_ERROR;
        ErrorResponse errorResponse = ErrorResponse.of(errorCode.getHttpStatus(),
                errorCode.getCode(),
                errorCode.getMessage(),
                e.getBindingResult());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(errorResponse);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity handleHttpRequestMethodNotSupportedExceptionn(HttpRequestMethodNotSupportedException e){
        log.error("[MethodArgumentNotValidException] cause: {}, message: {}",NestedExceptionUtils.getMostSpecificCause(e),e.getMessage());
        ErrorCode errorCode = CommonErrorCode.METHOD_NOT_ALLOWED;
        ErrorResponse errorResponse = ErrorResponse.of(errorCode.getHttpStatus(),
                errorCode.getCode(),
                errorCode.getMessage());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(errorResponse);
    }
}
