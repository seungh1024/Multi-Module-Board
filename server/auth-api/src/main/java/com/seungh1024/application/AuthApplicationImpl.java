package com.seungh1024.application;

import com.seungh1024.dto.MemberReqDto;
import com.seungh1024.encrypt.PasswordChecker;
import com.seungh1024.service.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;


/*
* 트랜잭션이 필요없는 서비스 클래스
* 컨트롤러는 무조건 application을 호출하고
* 트랜잭션 처리가 필요한 작업의 경우 여기서 service를 호출
*
* @Author 강승훈
* @Since 2023.03.20
* */
@Service
@RequiredArgsConstructor
public class AuthApplicationImpl implements AuthApplication {
    private final AuthServiceImpl authService;


    public void signup(MemberReqDto.JoinForm memberDto){
        authService.signup(memberDto);
    }

    // Login
    public HashMap<String,String> signin(MemberReqDto.LoginForm memberDto) {
        return authService.signin(memberDto);
    }

    // accessToken 재발급. Refresh Token으로만 재발급 함. 헤더에 Refresh Token만 넣어서 보내기.
    public String refreshAccessToken(String refreshToken, String memberId){
        return authService.refreshAccessToken(refreshToken,memberId);
    }

    //로그아웃 -> 저장한 토큰을 지워서 만료시킴
    //실패처리는 Filter에서 만료된 토큰을 보냈을 경우 걸러짐
    //나머지 아래 코드에서 에러가 발생하면 ExceptionHandlerAdvice로 가서 처리가 될 것임
    public void signOut(String memberId){
        authService.signOut(memberId);
    }
}
