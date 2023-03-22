package com.seungh1024.application;

import com.seungh1024.dto.MemberDto;
import com.seungh1024.member.Member;
import com.seungh1024.repository.MemberRepository;
import com.seungh1024.service.AuthService;
import com.seungh1024.utils.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
public class AuthApplication {
    private final AuthService authService;
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expired}")
    private long accessExpired;

    public void signup(MemberDto.JoinForm memberDto){
        authService.signup(memberDto);
    }

    public String signin(MemberDto.LoginForm memberDto) {
        String memberEmail = memberDto.getMemberEmail();

        Member member = memberRepository.findMemberByMemberEmail(memberEmail);
        if(member == null) throw new UsernameNotFoundException(memberEmail);
        if (!memberDto.getMemberPassword().equals(member.getMemberPassword())) throw new BadCredentialsException("");

        return jwtUtil.createJwt(memberEmail,jwtSecret,accessExpired);
    }
}
