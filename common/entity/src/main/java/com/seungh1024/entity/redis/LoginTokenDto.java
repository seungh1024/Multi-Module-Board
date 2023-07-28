package com.seungh1024.entity.redis;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.util.HashMap;

@Getter
@RedisHash(value = "refreshToken")
public class LoginTokenDto {
    @Id //key식별할 때의 고유 값. @RedisHash의 value와 결합하여 생성하며 변수명은 무조건 id이어야 함. 여기선 "refreshToken:{token}" 라고 나옴
    private String id;

    @Indexed // CRUD Repository사용할 때 JPA의 findBy필드명 처럼 사용하기 위한 어노테이션
    private String accessToken;

    @TimeToLive //유효시간 값으로 초 단위임. 밀리초로 바꾸고 싶다면 unit = TimeUnit.MILLISECONDS 옵션을 주면 됨
    private Long expiration;

    public LoginTokenDto(){}; // redis도 jpa 사용하니 기본 생성자가 필요하다.


    private LoginTokenDto(String memberId, String accessToken, Long expiration){
        this.id = memberId;
        this.accessToken = accessToken;
        this.expiration = expiration;
    }
    public LoginTokenDto(Long memberId, HashMap<String,String> tokens, long expiration){
        this.id = memberId+"";
        this.accessToken = tokens.get("accessToken");
        this.expiration = expiration;
    }

    public void updateAccessToken(String accessToken){
        this.accessToken = accessToken;
    }



}
