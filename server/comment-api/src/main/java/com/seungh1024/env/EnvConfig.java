package com.seungh1024.env;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/*
 * env 사용을 위한 Bean 등록
 *
 * @Author 강승훈
 * @Since 2023.07.14
 *
 * */
@Configuration
@PropertySource("classpath:properties/env.properties")
public class EnvConfig {
}

