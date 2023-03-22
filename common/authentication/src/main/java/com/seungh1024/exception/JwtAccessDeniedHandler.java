package com.seungh1024.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/*
 * 403에러 처리를 위한 핸들러
 *
 * @Author 강승훈
 * @Since 2023.03.21
 *
 * */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
            // TODO 권한 기능 넣으면 나중에 작성해야함
            response.sendError(403,"권한이 없습니다");
    }
}
