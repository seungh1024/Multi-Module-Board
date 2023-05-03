package com.seungh1024.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seungh1024.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/*
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
            setResponse(response,JwtErrorCode.TOKEN_NOT_EXIST);
        }
        else if(exception.equals(JwtErrorCode.TOKEN_EXPIRED_ERROR.name())){
            setResponse(response,JwtErrorCode.TOKEN_EXPIRED_ERROR);
        }else if(exception.equals(JwtErrorCode.TOKEN_SIGNATURE_ERROR.name())){
            setResponse(response,JwtErrorCode.TOKEN_SIGNATURE_ERROR);
        }else if(exception.equals(JwtErrorCode.TOKEN_NOT_CORRECT.name())){
            setResponse(response,JwtErrorCode.TOKEN_NOT_CORRECT);
        }
    }

    private void setResponse(HttpServletResponse response, JwtErrorCode jwtErrorCode) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset-UTF-8");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        ErrorResponse errorResponse = ErrorResponse.of(jwtErrorCode.getHttpStatus(),jwtErrorCode.getCode(),jwtErrorCode.getMessage());
        String result = new ObjectMapper().writeValueAsString(errorResponse);
        response.getWriter().write(result);
    }
}
