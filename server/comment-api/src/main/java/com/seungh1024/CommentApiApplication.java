package com.seungh1024;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/*
 *
 *
 * @Author 강승훈
 * @Since 2023.07.14
 *
 * */
@SpringBootApplication
@EnableJpaAuditing
public class CommentApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommentApiApplication.class,args);
    }
}
