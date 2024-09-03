package com.pick.nalsoom.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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

    @Autowired
    private final JwtTokenProvider jwtTokenProvider;

    SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .csrf(csrf -> csrf.disable()) // csrf 공격 해제
        .logout(out -> out.disable()) // 기본 로그아웃 해제
        .formLogin(form -> form.disable()) // formLogin 인증 해제
        .httpBasic(basic -> basic.disable()) // spring 기본 인증 해제
        .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 인증                                                                                               // 해제

        .authorizeHttpRequests((authorize) -> authorize
            .requestMatchers("/api/v1/good/**").authenticated() // good 요청은 인증된 사용자만
            .anyRequest().permitAll())

        .addFilterBefore(new JwtAuthorizationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
