package com.seungh1024.service;

import com.seungh1024.dto.MemberReqDto;
import com.seungh1024.encrypt.PasswordChecker;
import com.seungh1024.encrypt.RandomSalt;
import com.seungh1024.encrypt.SeunghPasswordEncoder;
import com.seungh1024.encrypt.dto.PasswordCheckerDto;
import com.seungh1024.entity.redis.LoginTokenDto;
import com.seungh1024.exception.custom.*;
import com.seungh1024.entity.member.Member;
import com.seungh1024.entity.member.MemberInfo;
import com.seungh1024.repository.auth.LoginTokenRepository;
import com.seungh1024.repository.member.MemberRepository;
import com.seungh1024.utils.JwtUtilImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/*
* 트랜잭션 처리가 필요한 서비스 클래스
*
* @Author 강승훈
* @Since 2023.03.20
* */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService{
    private final MemberRepository memberRepository;
    private final LoginTokenRepository loginTokenRepository;
    private final SeunghPasswordEncoder seunghPasswordEncoder;
    private final RandomSalt randomSalt;
    private final PasswordChecker passwordChecker;

    private final JwtUtilImpl jwtUtil;
    private final RedisTemplate redisTemplate;

    @Value("${jwt.secret}")
    private final String jwtSecret;
    @Value("${jwt.accessExpired}")
    private long accessExpired;
    @Value("${jwt.refreshExpired}")
    private long refreshExpired;
    @Override
    public HashMap<String, String> signin(MemberReqDto.LoginForm memberDto) {
        String memberEmail = memberDto.getMemberEmail();
        Member member = memberRepository.searchMember(memberEmail);
        if(member == null) throw new MemberNotFoundException();

        PasswordCheckerDto passwordCheckerDto = memberDto.toPasswordCheckerDto(member);
        if(!passwordChecker.isCorrectPassword(passwordCheckerDto)) throw new InvalidPasswordException();

        Long memberId = member.getMemberId();
        HashMap<String,String> tokens = jwtUtil.makeResponseTokens(memberId, memberEmail,jwtSecret,accessExpired,refreshExpired);

        LoginTokenDto loginTokenDto = new LoginTokenDto(memberId,tokens,refreshExpired);
        loginTokenRepository.save(loginTokenDto);

        return tokens;
    }

    @Override
    @Transactional(readOnly = false)
    public String refreshAccessToken(String refreshToken, String memberId) {
        LoginTokenDto loginTokenDto = loginTokenRepository.findById(memberId).orElseGet(()->{
            throw new TokenExpiredException();
        });

        String memberEmail = jwtUtil.getMemberEmailJwt(refreshToken,jwtSecret);

        String accessToken = loginTokenDto.getAccessToken();
        String newAccessToken = null;
        Long remainingTime = null;
        try{
            // 토큰이 만료되지 않으면 에러가 발생하지 않음.
            if(!jwtUtil.isExpiredJwt(accessToken,jwtSecret)){
                //access token을 새로 생성할 이유가 없는데 재발급을 하니 refresh token이 탈취되었다 가정하고 토큰을 만료 시킨다.
                loginTokenRepository.delete(loginTokenDto);
                //access token도 블랙 리스트로 등록한다. 사용자 이메일을 key값으로 등록해서 사용
                remainingTime = jwtUtil.getExpiredJwt(accessToken,jwtSecret).getTime() - System.currentTimeMillis();
//                LoginTokenDto blackList = new LoginTokenDto(accessToken, null,remainingTime/1000);
                //access token 하나 저장하는데 hashMap 저장하는게 너무 비효율 같아 template이용
                redisTemplate.opsForValue().set(memberId,accessToken,remainingTime, TimeUnit.MILLISECONDS);

            }
        }catch(Exception e){
            // access token이 만료되었으면 정상적으로 발급 진행
            newAccessToken = jwtUtil.createAccessJwt(Long.parseLong(memberId),memberEmail,jwtSecret,accessExpired);
            // accessToken만 업데이트 진행
            loginTokenDto.updateAccessToken(newAccessToken);
            loginTokenRepository.save(loginTokenDto);
        }

        // 새로 발급하는 토큰에 아무것도 없으면 잘못된 접근 -> 강제 로그아웃 시킴
        if(newAccessToken == null){
            throw new InvalidAccessException();
        }

        return newAccessToken;
    }

    @Override
    @Transactional(readOnly = false)
    public void signup(MemberReqDto.JoinForm memberDto){
        Member member = memberRepository.searchMember(memberDto.getMemberEmail());
        if(member != null){ //이미 존재하면 예외처리
            throw new DuplicateMemberException();
        }

        String salt = randomSalt.getSalt();
        String encodedPassword = seunghPasswordEncoder.encryptPassword(memberDto.getMemberPassword(),salt);

        MemberInfo memberInfo = memberDto.toMemberInfo();
        Member saveMember = memberDto.toMember(encodedPassword,salt,memberInfo);
        memberRepository.save(saveMember);
    }

    @Override
    public void signOut(String memberId) {
        LoginTokenDto loginTokenDto = loginTokenRepository.findById(memberId).orElseGet(()->{
            throw new TokenExpiredException();
        });

        loginTokenRepository.delete(loginTokenDto);

    }
}
