package com.seungh1024.application;

import com.seungh1024.dto.MemberReqDto;

import java.util.HashMap;

/*
 * AuthApplication 인터페이스
 *
 *  @Author 강승훈
 *  @Since 2023.06.01
 *
 * */
public interface AuthApplication {
    void signup(MemberReqDto.JoinForm memberDto);

    // Login
    HashMap<String,String> signin(MemberReqDto.LoginForm memberDto);

    // accessToken 재발급. Refresh Token으로만 재발급 함. 헤더에 Refresh Token만 넣어서 보내기.
    String refreshAccessToken(String refreshToken, String memberId);

    //로그아웃 -> 저장한 토큰을 지워서 만료시킴
    //실패처리는 Filter에서 만료된 토큰을 보냈을 경우 걸러짐
    //나머지 아래 코드에서 에러가 발생하면 ExceptionHandlerAdvice로 가서 처리가 될 것임
    void signOut(String memberId);
}
