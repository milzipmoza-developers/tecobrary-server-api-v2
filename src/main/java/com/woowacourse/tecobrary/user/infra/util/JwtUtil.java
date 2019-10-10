package com.woowacourse.tecobrary.user.infra.util;

import com.woowacourse.tecobrary.user.ui.vo.ResponseUserVo;
import io.jsonwebtoken.Claims;
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
public class JwtUtil implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;

    private static final long JWT_TOKEN_VALIDITY = 60 * 60 * 24 * 7; // one week

    private final String secret;

    @Autowired
    public JwtUtil(@Value("${jwt.secret}") String secret) {
        this.secret = secret;
    }

    public String generateToken(ResponseUserVo responseUserVo) {
        Map<String, Object> claims = new LinkedHashMap<>();
        claims.put("id", responseUserVo.getUserNo());
        claims.put("email", responseUserVo.getEmail());
        claims.put("name", responseUserVo.getName());
        claims.put("authorization", responseUserVo.getAuthorization());
        claims.put("avatarUrl", responseUserVo.getAvatarUrl());

        Map<String, Object> headers = new LinkedHashMap<>();
        headers.put("alg", "HS256");
        headers.put("typ", "JWT");
        return doGenerateToken(claims, headers);
    }

    public Boolean validateToken(String token, ResponseUserVo responseUserVo) {
        final String userNo = getUserNoFromToken(token);
        return (userNo.equals(responseUserVo.getUserNo()) && !isTokenExpired(token));
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

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private String getUserNoFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
}
