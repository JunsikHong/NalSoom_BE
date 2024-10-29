package com.pick.nalsoom.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtTokenProvider {

    @Value("${custom.jwt.auth_header}")
    public String auth_header;

    @Value("${custom.jwt.token_type}")
    public String token_type;

    @Value("${custom.jwt.secret}")
    private String jwtSecretKey;

    @Value("${custom.jwt.expiration_time}")
    private Long expirationTime;

    @Value("${custom.jwt.refresh_time}")
    private Long refreshTime;

    // jwt 발급
    public JwtToken createJwtToken(UserDetailsImpl userDetailsImpl) {
        Claims claims = getClaims(userDetailsImpl);
        String accessToken = getToken(userDetailsImpl, claims, expirationTime);
        String refreshToken = getToken(userDetailsImpl, claims, refreshTime);
        return new JwtToken(accessToken, refreshToken, token_type);
    }

    // 토큰 얻기
    private String getToken(UserDetailsImpl userDetailsImpl, Claims claims, Long validationSecond) {
        long now = new Date().getTime();

        return Jwts.builder()
                .setSubject(userDetailsImpl.getUsername())
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, jwtSecretKey)
                .setExpiration(new Date(now + validationSecond))
                .compact();
    }

    // id 데이터 저장한 클레임 객체 얻기
    public Claims getClaims(UserDetailsImpl userDetailsImpl) {
        Claims claims = Jwts.claims();
        claims.put("userId", userDetailsImpl.getUsername());
        return claims;
    }

    // HTTP 요청에서 토큰 추출
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    // 토큰에서 사용자 ID 추출
    public String getUserIdFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token).getBody().get("userId", String.class);
    }

    // 유효한 토큰인지 확인 해주는 메서드
    public boolean isValidToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException exception) {
            return false;
        } catch (JwtException exception) {
            return false;
        } catch (NullPointerException exception) {
            return false;
        }
    }
}