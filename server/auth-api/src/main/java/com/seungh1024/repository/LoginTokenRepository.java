package com.seungh1024.repository;

import com.seungh1024.redis.LoginTokenDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LoginTokenRepository extends CrudRepository<LoginTokenDto,String> {
    Optional<LoginTokenDto> findLoginTokenDtoByAccessToken(String accessToken); // acceessToken으로 정보 추출

}
