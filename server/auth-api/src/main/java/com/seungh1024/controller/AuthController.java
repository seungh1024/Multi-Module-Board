package com.seungh1024.controller;


/*
* 회원가입, 로그인 클래스
*
* @Author 강승훈
* @Since 2023.03.20
* */

import com.seungh1024.Response;
import com.seungh1024.application.AuthApplication;
import com.seungh1024.dto.MemberDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
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
    private final AuthApplication authApplication;

    //회원 가입
    @PostMapping("/signup")
    public Response<?> signup(@RequestBody @Valid MemberDto.JoinForm memberDto){
        authApplication.signup(memberDto);
        return success("회원 가입 성공");
    }

    //로그인
    @PostMapping("/signin")
    public Response<?> signin(@RequestBody @Valid MemberDto.LoginForm memberDto){
        String accessToken = authApplication.signin(memberDto);
        HashMap<String,String> token = new HashMap<>();
        token.put("accessToken",accessToken);
        return success(token);
    }

    //security test
    @GetMapping("/security")
    public Response<?> securityTest(Authentication authentication){
        String memberEmail = authentication.getName();
        return success(memberEmail + " Authentication에서 인증된 이메일 꺼내오기!");
    }


}
