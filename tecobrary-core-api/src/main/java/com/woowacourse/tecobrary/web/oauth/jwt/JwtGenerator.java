package com.woowacourse.tecobrary.web.oauth.jwt;

import com.woowacourse.tecobrary.web.github.dto.UserJwtInfoDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtGenerator {

    private static final long JWT_TOKEN_VALIDITY = 60 * 60 * 24 * 7; // one week

    private final String secret;

    public JwtGenerator(@Value("jwt.secret") String secret) {
        this.secret = secret;
    }

    public String generateToken(UserJwtInfoDto userJwtInfoVo) {
        Map<String, Object> claims = new LinkedHashMap<>();
        claims.put("id", userJwtInfoVo.getId());
        claims.put("email", userJwtInfoVo.getEmail());
        claims.put("name", userJwtInfoVo.getName());
        claims.put("authorization", userJwtInfoVo.getAuthorization());
        claims.put("avatarUrl", userJwtInfoVo.getAvatarUrl());

        Map<String, Object> headers = new LinkedHashMap<>();
        headers.put("alg", "HS256");
        headers.put("typ", "JWT");
        return doGenerateToken(claims, headers);
    }

    public Boolean validateToken(String token, UserJwtInfoDto userJwtInfoVo) {
        String userNo = getUserIdFromToken(token);
        return (userNo.equals(userJwtInfoVo.getId()) && !isTokenExpired(token));
    }

    public Boolean isTokenExpired(String token) {
        try {
            Date expiration = getExpirationDateFromToken(token);
            return expiration.before(new Date());
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    public String getUserIdFromToken(String token) {
        return (String) getClaimFromToken(token, claims -> claims.get("id"));
    }

    private String doGenerateToken(Map<String, Object> claims, Map<String, Object> headers) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, secret)
                .setHeader(headers)
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .compact();
    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
