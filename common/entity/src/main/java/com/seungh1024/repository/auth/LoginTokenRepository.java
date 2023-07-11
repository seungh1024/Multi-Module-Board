package com.seungh1024.repository.auth;


import com.seungh1024.entity.redis.LoginTokenDto;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LoginTokenRepository extends CrudRepository<LoginTokenDto,String> {
}
