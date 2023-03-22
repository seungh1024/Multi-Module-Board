package com.seungh1024.env;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/*
 * env 사용을 위한 Bean 등록
 *
 * @Author 강승훈
 * @Since 2023.03.20
 *
 * */
@Configuration
@PropertySource("classpath:properties/env.properties")
public class EnvConfig {
}
