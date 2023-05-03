package com.seungh1024.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;


/*
 * JWT util 클래스
 *
 *  @Author 강승훈
 *  @Since 2023.03.21
 *
 * */

@Component
public class JwtUtil {

    public String createAccessJwt(String memberEmail, String jwtSecret, Long accessExpired){
        // 원하는 정보를 담기 위해 jwt에서 Claim이라는 공간을 제공해 줌. 여기다 정보를 담을 것임
        Claims claims = Jwts.claims();

        // 일종의 Map 자료구조와 같음
        claims.put("memberEmail",memberEmail);

        return Jwts.builder()
                .setClaims(claims) // 만들어 놓은 claim을 넣는 것
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + accessExpired))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();

    }

    public String createRefreshJwt(String memberEmail, String jwtSecret, Long refreshExpired){
        Claims claims = Jwts.claims();
        claims.put("memberEmail",memberEmail);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+refreshExpired))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public boolean isExpired(String token,String jwtSecret) {
        //before()로 현재 시간을 넣어줘서 만료 기한이 현재 시간 전이면 만료된 것!. before() 가 true면 만료된 것이다.
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token)
                .getBody().getExpiration().before(new Date());
    }

    public String getMemberEmail(String token, String jwtSecret){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token)
                .getBody().get("memberEmail",String.class);
    }

    public Date getExpired(String token, String jwtSecret){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token)
                .getBody().getExpiration();
    }
}
