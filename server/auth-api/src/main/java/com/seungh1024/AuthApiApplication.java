package com.seungh1024;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/*
 * @Author 강승훈
 * @Since 2023.03.20
 *
 * */
@SpringBootApplication
public class AuthApiApplication {
    public static void main(String[] args){
        SpringApplication.run(AuthApiApplication.class,args);
    }
}
