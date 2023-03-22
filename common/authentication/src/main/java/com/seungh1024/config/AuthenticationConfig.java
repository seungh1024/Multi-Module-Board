package com.seungh1024.config;

import com.seungh1024.exception.JwtAccessDeniedHandler;
import com.seungh1024.exception.JwtAuthenticationEntryPoint;
import com.seungh1024.utils.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
 * Security 설정 클래스
 *
 *  @Author 강승훈
 *  @Since 2023.03.20
 *
 * */

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AuthenticationConfig {
    @Value("${jwt.secret}")
    private String jwtSecret;
    private final JwtUtil jwtUtil;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .httpBasic().disable()///HttpBasic 인증을 할 수 있는 기능을 비활성화
                .csrf().disable()
                .cors().and()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/signup","/api/v1/auth/signin").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //jwt 사용하는 경우 사용. 세션 사용하지 않기 위한 옵션
                .and()
                // 만든 필터를 등록해준다.
                // usernamePasswordAuthenticationFilter보다 JwtFilter가 앞에 있는 이유는
                // 로그인할 때 사용할 필터가 아니라 로그인 후 토큰을 검증하고 권한을 부여하는 과정이므로
                // 직접 구현한 JwtFilter를 먼저 실행시켜야 함
                // UsernamePasswordAuthenticationFilter 직전에 걸리도록 설정한 것
                // Spring Security에서 기본적으로 요청을 막고 아이디 비밀번호를 통해 인증을 하는데 그게 UsernamePasswordAuthenticationFilte임
                // 그러니까 저 필터가 무조건 실행되니까 그 이전에 우리가 만든 JwtFilter를 실행하고 인증 도장을 쾅 박고 UsernamePasswordToken을 넘겨줌으로써 대신하는 것
                .exceptionHandling()
                .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                .accessDeniedHandler(new JwtAccessDeniedHandler())
                .and()
                .addFilterBefore(new JwtFilter(jwtSecret,jwtUtil), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
