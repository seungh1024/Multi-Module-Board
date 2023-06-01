package com.seungh1024.service;

import com.seungh1024.dto.MemberReqDto;

/*
 * AuthService 인터페이스
 *
 *  @Author 강승훈
 *  @Since 2023.06.01
 *
 * */
public interface AuthService {
    void signup(MemberReqDto.JoinForm memberDto);
}
