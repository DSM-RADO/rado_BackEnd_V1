package com.example.rado.global.security.Jwt;

import com.example.rado.domain.user.domain.User;
import com.example.rado.global.error.exception.ExpiredTokenException;
import com.example.rado.global.security.Jwt.dto.TokenResponse;
import com.example.rado.global.security.principle.AuthDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtProvider {

    // 헤더 이름을 저장하는 상수 변수입니다. 이 헤더에 토큰이 포함
    private static final String HEADER = "Authorization";

    // 토큰 접두사를 저장하는 상수 변수입니다. 일반적으로 "Bearer"로 설정
    private static final String PREFIX = "Bearer";

    // AuthDetailsService의 의존성을 주입받습니다. (의존성 주입을 사용하기 위해 final 키워드를 사용합니다.)
    private final AuthDetailsService authDetailsService;

    // spring.jwt.key 프로퍼티의 값을 주입
    @Value("${spring.jwt.key}")
    private String key;

    // spring.jwt.live.accessToken 프로퍼티의 값을 주입
    @Value("${spring.jwt.live.accessToken}")
    private Long accessTokenTime;

    // 초기화 메서드로, 객체 생성 후 프로퍼티가 초기화되었을 때 실행됩니다. key를 Base64로 인코딩하여 재할당합니다.
    @PostConstruct
    protected void init() {
        key = Base64.getEncoder().encodeToString(key.getBytes());
    }

    // 주어진 사용자에 대한 토큰을 생성하여 TokenResponse 객체로 반환
    public TokenResponse getToken(User user) {
        String accessToken = generateAccessToken(user.getUserId());

        return new TokenResponse(accessToken, accessTokenTime);
    }

    // 주어진 사용자 ID로 액세스 토큰을 생성
    public String generateAccessToken(String userId) {
        return generateToken(userId, "access", accessTokenTime);
    }

    // 주어진 사용자 ID, 타입, 만료 시간으로 토큰을 생성
    private String generateToken(String userId, String type, Long exp) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, key.getBytes())
                .setSubject(userId)
                .claim("type", type)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + exp * 1000))
                .compact();
    }

    // 요청의 헤더에서 토큰을 추출
    public String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader(HEADER);

        return parseToken(bearer);
    }

    // 토큰을 사용하여 Authentication 객체를 생성
    public Authentication authentication(String token) {
        UserDetails userDetails = authDetailsService.loadUserByUsername(getTokenSubject(token));

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // "Bearer" 접두사를 제거하여 토큰을 파싱.
    public String parseToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith(PREFIX))
            return bearerToken.replace(PREFIX, "");

        return null;
    }

    // 키를 사용하여 토큰 본문을 추출
    private Claims getTokenBody(String token) {
        try {
            return Jwts.parser().setSigningKey(key.getBytes())
                    .parseClaimsJws(token).getBody();
        } catch (Exception e) {
            throw ExpiredTokenException.EXCEPTION;
        }
    }

    // 토큰 본문에서 주제(사용자 ID)를 가져옴
    private String getTokenSubject(String token) {
        return getTokenBody(token).getSubject();
    }
}
