package com.seungh1024.service;

import com.seungh1024.dto.MemberReqDto;

import java.util.HashMap;

/*
 * AuthService 인터페이스
 *
 *  @Author 강승훈
 *  @Since 2023.06.01
 *
 * */
public interface AuthService {
    HashMap<String,String> signin(MemberReqDto.LoginForm memberDto);
    String refreshAccessToken(String refreshToken, String memberId);
    void signup(MemberReqDto.JoinForm memberDto);
    void signOut(String memberId);
}
