package com.woowacourse.tecobrary.user.infra.util;

import com.woowacourse.tecobrary.user.ui.vo.UserJwtInfoVo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;

    private static final long JWT_TOKEN_VALIDITY = 60 * 60 * 24 * 7; // one week

    private final String secret;

    @Autowired
    public JwtUtils(@Value("${jwt.secret}") String secret) {
        this.secret = secret;
    }

    public String generateToken(UserJwtInfoVo userJwtInfoVo) {
        Map<String, Object> claims = new LinkedHashMap<>();
        claims.put("id", userJwtInfoVo.getUserNo());
        claims.put("email", userJwtInfoVo.getEmail());
        claims.put("name", userJwtInfoVo.getName());
        claims.put("authorization", userJwtInfoVo.getAuthorization());
        claims.put("avatarUrl", userJwtInfoVo.getAvatarUrl());

        Map<String, Object> headers = new LinkedHashMap<>();
        headers.put("alg", "HS256");
        headers.put("typ", "JWT");
        return doGenerateToken(claims, headers);
    }

    public Boolean validateToken(String token, UserJwtInfoVo userJwtInfoVo) {
        final String userNo = getUserIdFromToken(token);
        return (userNo.equals(userJwtInfoVo.getUserNo()) && !isTokenExpired(token));
    }

    public Boolean isTokenExpired(String token) {
        try {
            final Date expiration = getExpirationDateFromToken(token);
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
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
