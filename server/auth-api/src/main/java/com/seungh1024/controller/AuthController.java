package com.seungh1024.controller;



import com.seungh1024.Response;
import com.seungh1024.application.AuthApplicationImpl;
import com.seungh1024.dto.MemberReqDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.seungh1024.Response.success;


/*
 * 회원가입, 로그인 클래스
 *
 * @Author 강승훈
 * @Since 2023.03.20
 * */

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthApplicationImpl authApplication;

    //회원 가입
    @PostMapping("/signup")
    public Response<?> signup(@RequestBody @Valid MemberReqDto.JoinForm memberDto){
        authApplication.signup(memberDto);
        return success("회원 가입 성공");
    }

    //로그인
    @PostMapping("/signin")
    public Response<?> signin(@RequestBody @Valid MemberReqDto.LoginForm memberDto){
        HashMap<String,String> data = authApplication.signin(memberDto);
        return success(data);
    }

    //refreshToken을 헤더에 넣어서 보내고 유효하면 accessToken발급
    @GetMapping("/refresh")
    public Response<?> refreshToken(HttpServletRequest request){
        String refreshToken = request.getHeader(HttpHeaders.AUTHORIZATION).split(" ")[1];
        HashMap<String,String> accessToken = new HashMap<>();
        accessToken.put("accessToken",authApplication.refreshAccessToken(refreshToken));
        return success(accessToken);
    }

    // 로그아웃
    @GetMapping("/signout")
    public Response<?> signOut(HttpServletRequest request){
        String accessToken = request.getHeader(HttpHeaders.AUTHORIZATION).split(" ")[1];
        authApplication.signOut(accessToken);
        return success();
    }

    //security test
    @GetMapping("/security")
    public Response<?> securityTest(Authentication authentication){
        String memberEmail = authentication.getName();
        return success(memberEmail + " Authentication에서 인증된 이메일 꺼내오기!");
    }




}
