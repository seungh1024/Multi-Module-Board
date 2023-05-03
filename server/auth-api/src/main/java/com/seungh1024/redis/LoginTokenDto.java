package com.seungh1024.redis;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@RedisHash(value = "refreshToken")
public class LoginTokenDto {
    @Id //key식별할 때의 고유 값. @RedisHash의 value와 결합하여 생성하며 변수명은 무조건 id이어야 함. 여기선 "refreshToken:{token}" 라고 나옴
    private String id;

    @Indexed // CRUD Repository사용할 때 JPA의 findBy필드명 처럼 사용하기 위한 어노테이션
    private String accessToken;

    @TimeToLive //유효시간 값으로 초 단위임. 밀리초로 바꾸고 싶다면 unit = TimeUnit.MILLISECONDS 옵션을 주면 됨
    private Long expiration;

    protected LoginTokenDto(){}; // redis도 jpa 사용하니 기본 생성자가 필요하다.


    private LoginTokenDto(String refreshToken, String accessToken, Long expiration){
        this.id = refreshToken;
        this.accessToken = accessToken;
        this.expiration = expiration;
    }
    public static LoginTokenDto createAccessRefreshToken(String refreshToken, String accessToken, long expiredTime){
        return new LoginTokenDto(refreshToken,accessToken,expiredTime/1000);
    }

    public void updateAccessToken(String accessToken){
        this.accessToken = accessToken;
    }



}
