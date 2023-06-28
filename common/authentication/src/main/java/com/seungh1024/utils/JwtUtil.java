package com.seungh1024.utils;

import java.util.Date;

/*
 * JWT util 인터페이스
 *
 *  @Author 강승훈
 *  @Since 2023.06.01
 *
 * */

public interface JwtUtil {
    String createAccessJwt(Long memberId,String memberEmail, String jwtSecret, Long accessExpired);

    String createRefreshJwt(Long memberId, String memberEmail, String jwtSecret, Long refreshExpired);

    boolean isExpiredJwt(String token,String jwtSecret);

    Long getMemberId(String token, String jwtSecret);
    String getMemberEmailJwt(String token, String jwtSecret);

    Date getExpiredJwt(String token, String jwtSecret);

}
