package com.seungh1024.env;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/*
 * Env 설정 파일
 *
 * @Author 강승훈
 * @Since 2023.06.08
 *
 * */
@Configuration
@PropertySource("classpath:properties/env.properties")
public class EnvConfig {
}
