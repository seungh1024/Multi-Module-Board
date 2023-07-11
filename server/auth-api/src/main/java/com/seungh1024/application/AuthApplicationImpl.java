package com.seungh1024.application;

import com.seungh1024.dto.MemberReqDto;
import com.seungh1024.encrypt.PasswordChecker;
import com.seungh1024.entity.member.Member;
import com.seungh1024.entity.redis.LoginTokenDto;
import com.seungh1024.repository.auth.LoginTokenRepository;
import com.seungh1024.repository.member.MemberRepository;
import com.seungh1024.service.AuthServiceImpl;
import com.seungh1024.utils.JwtUtilImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


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
    private final MemberRepository memberRepository;
    private final LoginTokenRepository loginTokenRepository;
    private final JwtUtilImpl jwtUtil;
    private final RedisTemplate redisTemplate;
    private final PasswordChecker passwordChecker;

    @Value("${jwt.secret}")
    private final String jwtSecret;
    @Value("${jwt.accessExpired}")
    private long accessExpired;
    @Value("${jwt.refreshExpired}")
    private long refreshExpired;

    public void signup(MemberReqDto.JoinForm memberDto){
        authService.signup(memberDto);
    }

    // Login
    public HashMap<String,String> signin(MemberReqDto.LoginForm memberDto) {
        String memberEmail = memberDto.getMemberEmail();

        Member member = memberRepository.findMemberByMemberEmail(memberEmail);
        if(member == null) throw new UsernameNotFoundException(memberEmail);
//        if (!memberDto.getMemberPassword().equals(member.getMemberPassword())) throw new BadCredentialsException("");
        if(!passwordChecker.isCorrectPassword(memberDto.getMemberPassword(),member.getMemberPassword(),member.getMemberSalt())) throw new BadCredentialsException("");
        Long memberId = member.getMemberId();

        HashMap<String,String> tokens = new HashMap<>();
        String accessToken = jwtUtil.createAccessJwt(memberId,memberEmail,jwtSecret,accessExpired);
        String refreshToken = jwtUtil.createRefreshJwt(memberId,memberEmail,jwtSecret,refreshExpired);
        tokens.put("accessToken",accessToken);
        tokens.put("refreshToken",refreshToken);

        LoginTokenDto loginTokenDto = LoginTokenDto.createAccessRefreshToken(memberId+"",accessToken,refreshExpired);
        loginTokenRepository.save(loginTokenDto);

        return tokens;
    }

    // accessToken 재발급. Refresh Token으로만 재발급 함. 헤더에 Refresh Token만 넣어서 보내기.
    public String refreshAccessToken(String refreshToken, String memberId){
        Optional<LoginTokenDto> loginTokenDto = loginTokenRepository.findById(memberId);

        if(loginTokenDto.isEmpty()){ //없으면 로그아웃 처리 -> refresh token이 만료된 것
            throw new AccountExpiredException("토큰이 만료되어 ");
        }
        String memberEmail = jwtUtil.getMemberEmailJwt(refreshToken,jwtSecret);

        String accessToken = loginTokenDto.get().getAccessToken();
        String newAccessToken = null;
        Long remainingTime = null;
        try{
            // 토큰이 만료되지 않으면 에러가 발생하지 않음.
            if(!jwtUtil.isExpiredJwt(accessToken,jwtSecret)){
                //access token을 새로 생성할 이유가 없는데 재발급을 하니 refresh token이 탈취되었다 가정하고 토큰을 만료 시킨다.
                loginTokenRepository.delete(loginTokenDto.get());
                //access token도 블랙 리스트로 등록한다. 사용자 이메일을 key값으로 등록해서 사용
                remainingTime = jwtUtil.getExpiredJwt(accessToken,jwtSecret).getTime() - System.currentTimeMillis();
//                LoginTokenDto blackList = new LoginTokenDto(accessToken, null,remainingTime/1000);
                //access token 하나 저장하는데 hashMap 저장하는게 너무 비효율 같아 template이용
                redisTemplate.opsForValue().set(memberId,accessToken,remainingTime, TimeUnit.MILLISECONDS);

            }
        }catch(Exception e){
//            e.printStackTrace();
            // access token이 만료되었으면 정상적으로 발급 진행
            newAccessToken = jwtUtil.createAccessJwt(Long.parseLong(memberId),memberEmail,jwtSecret,accessExpired);
            // accessToken만 업데이트 진행
            loginTokenDto.get().updateAccessToken(newAccessToken);
            loginTokenRepository.save(loginTokenDto.get());
        }

        // 새로 발급하는 토큰에 아무것도 없으면 잘못된 접근 -> 강제 로그아웃 시킴
        if(newAccessToken == null){
            throw new AccountExpiredException("잘못된 접근으로 ");
        }

        return newAccessToken;
    }

    //로그아웃 -> 저장한 토큰을 지워서 만료시킴
    //실패처리는 Filter에서 만료된 토큰을 보냈을 경우 걸러짐
    //나머지 아래 코드에서 에러가 발생하면 ExceptionHandlerAdvice로 가서 처리가 될 것임
    public void signOut(String memberId){
//        Optional<LoginTokenDto> loginTokenDto = loginTokenRepository.findLoginTokenDtoByAccessToken(accessToken);
        Optional<LoginTokenDto> loginTokenDto = loginTokenRepository.findById(memberId);
        loginTokenRepository.delete(loginTokenDto.get());
    }
}
