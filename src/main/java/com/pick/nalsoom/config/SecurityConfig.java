package com.pick.nalsoom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.pick.nalsoom.jwt.JwtAuthorizationFilter;
import com.pick.nalsoom.jwt.JwtTokenProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .csrf(AbstractHttpConfigurer::disable) // csrf 공격 해제
        .logout(AbstractHttpConfigurer::disable) // 기본 로그아웃 해제
        .formLogin(AbstractHttpConfigurer::disable) // formLogin 인증 해제
        .httpBasic(AbstractHttpConfigurer::disable) // spring 기본 인증 해제
        .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 인증                                                                                               // 해제

        .authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/api/v1/user/**").permitAll() //유저 인증 요청은 모두 permit
                .requestMatchers(HttpMethod.GET, "/api/v1/board/shelter/**").permitAll() //대피소 게시판 GET 요청은 모두 permit
                .requestMatchers("/api/v1/review/**").permitAll() //대피소 게시판에 표시 될 리뷰 GET 요청은 모두 permit
                .requestMatchers("/api/v1/good/**").permitAll()
                .anyRequest().authenticated()) //그 외에는 모두 회원 기능

        .addFilterBefore(new JwtAuthorizationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
