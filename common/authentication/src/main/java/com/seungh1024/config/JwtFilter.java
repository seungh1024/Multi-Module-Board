package com.seungh1024.config;

import com.seungh1024.exception.JwtErrorCode;
import com.seungh1024.utils.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/*
 * Security Filter에 사용할 JwtFilter 클래스
 *
 *  @Author 강승훈
 *  @Since 2023.03.21
 *
 * */

//Filter를 사용하지 않은 이유가 Spring Security의 인증,접근 제어가 Filter로 구현되고
// 이런 인증,접근 제어는 RequestDispatcher 클래스에 의해 다른 서블릿으로 dispatch되는데
// 이때 이동할 서블릿에 도착하기 전에 다시 한 번 filter chain을 거치며 필터가 두 번 실행되는 현상이 발생할 수 있다.
// 이런 문제점을 해결하기 위해 oncePerRequestFilter를 사용하며 모든 서블릿에 일관된 요청을 처리하기 위해 만들어진 필터이다.
// 이 추상 클래스를 구현한 필터는 사용자의 한 번의 요청에 한 번 실행되는 필터를 만들 수 있다.

// 현재 Spring Security에 의해 대부분의 요청들이 막혀있는데 이 필터가 검문소 역할을 하는 것
// 여기서 인증에 성공하면 통과할 수 있게 하는 것이다. -> 권한을 부여하는 것
@Slf4j
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private String jwtSecret;
    private JwtUtil jwtUtil;
    private StringRedisTemplate redisTemplate;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorization : {}", authorization);

        //권한이 없거나 Bearer 형태로 보내지 않으면 block처리
        //권한을 안넣어준 요청,응답 객체를 필터에 넣어줘야함
        if(authorization == null || !authorization.startsWith("Bearer ")){
            log.error("authorization이 없습니다.");
            filterChain.doFilter(request,response);
            return;
        }

        // Token 꺼내기
        // "Bearer {token}" 형식이기 때문에 공백으로 split 후 1번째걸 가져와야 token을 가져옴!
        String token = authorization.split(" ")[1];

        // Token expired 여부
        try{
            //유효한 토큰인 경우
            if(!jwtUtil.isExpired(token,jwtSecret)){
                // Token에서 사용자 정보 꺼내기
                String memberEmail = jwtUtil.getMemberEmail(token,jwtSecret);
                String blackListToken = redisTemplate.opsForValue().get(token);

//                String blackListToken = (String)valueOperations.get("refreshToken:"+token,"id");
                // 블랙 리스트 토큰이 존재하고, 해당 사용자 이름으로 등록된 토큰과 요청한 토큰이 같다면 예외 발생
                if(blackListToken != null && blackListToken.equals(token)){
                    throw new AccountExpiredException(""); //만료됐다는 에러를 발생시키고 아래에서 처리함
                }
                //권한 부여
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(memberEmail,null, List.of(new SimpleGrantedAuthority("USER")));

                // request를 넣어 detail을 빌드하고 추가한다.
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // securitycontextholder.getContext()로 SecurityContext에 접근하고 Authentication에 접근하여 토큰을 등록한다.
                // AuthenticationManager에 등록하는 과정이다.
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }catch(ExpiredJwtException e){
            request.setAttribute("exception", JwtErrorCode.TOKEN_EXPIRED_ERROR.name());
        }catch(SignatureException e) {
            request.setAttribute("exception", JwtErrorCode.TOKEN_SIGNATURE_ERROR.name());
        }catch(MalformedJwtException e){
            request.setAttribute("exception", JwtErrorCode.TOKEN_NOT_CORRECT.name());
        }catch(AccountExpiredException e){
            request.setAttribute("exception", JwtErrorCode.TOKEN_EXPIRED_ERROR.name()); //토큰 만료와 같은 에러를 던져줌
        }
        catch (Exception e){
            log.error("[Exception] cause: {} , message: {}", NestedExceptionUtils.getMostSpecificCause(e), e.getMessage());
            e.printStackTrace();
        }

        // filterChain에 request,response를 넘겨준다.
        // 넘겨주면 request객체에 인증이 되었다고 인증 도장이 찍히는 형태이다.
        filterChain.doFilter(request,response);
    }
}
