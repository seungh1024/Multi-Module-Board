package com.seungh1024;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/*
 * @Author 강승훈
 * @Since 2023.06.08
 *
 * */
@SpringBootApplication
@EnableJpaAuditing // Spring Data JPA가 Auditing 사용하게 해주는 어노테이션
public class BoardApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(BoardApiApplication.class,args);
    }
}
