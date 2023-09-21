package com.seungh1024.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seungh1024.ErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 401에러 처리를 위한 핸들러. 토큰의 만료, 잘못된 토큰, 지원되지 않는 토큰에 대한 처리
 * TODO 다른 토큰 관련 에러 처리. 현재 유효기간, 잘못된 토큰만 있음
 *
 * @Author 강승훈
 * @Since 2023.03.21
 *
 * */

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException , ServletException{
        String exception = (String)request.getAttribute("exception");

        if(exception == null){
            setResponse(response,FilterErrorCode.TOKEN_NOT_EXIST);
        }
        else if(exception.equals(FilterErrorCode.TOKEN_EXPIRED_ERROR.name())){
            setResponse(response,FilterErrorCode.TOKEN_EXPIRED_ERROR);
        }else if(exception.equals(FilterErrorCode.TOKEN_SIGNATURE_ERROR.name())){
            setResponse(response,FilterErrorCode.TOKEN_SIGNATURE_ERROR);
        }else if(exception.equals(FilterErrorCode.TOKEN_NOT_CORRECT.name())){
            setResponse(response,FilterErrorCode.TOKEN_NOT_CORRECT);
        }else if(exception.equals(FilterErrorCode.DATABASE_CONNECTION_ERROR.name())){
            setResponse(response,FilterErrorCode.DATABASE_CONNECTION_ERROR);
        }
    }

    private void setResponse(HttpServletResponse response, FilterErrorCode filterErrorCode) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset-UTF-8");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        ErrorResponse errorResponse = ErrorResponse.of(filterErrorCode.getHttpStatus(),filterErrorCode.getCode(),filterErrorCode.getMessage());
        String result = new ObjectMapper().writeValueAsString(errorResponse);
        response.getWriter().write(result);
    }
}
