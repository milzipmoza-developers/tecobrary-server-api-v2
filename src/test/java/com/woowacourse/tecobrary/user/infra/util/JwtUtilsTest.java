package com.woowacourse.tecobrary.user.infra.util;

import com.woowacourse.tecobrary.user.ui.vo.UserJwtInfoVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class JwtUtilsTest {

    private static final Logger log = LoggerFactory.getLogger(JwtUtilsTest.class);

    private static final String EXPIRED_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
            "eyJpZCI6IjEiLCJlbWFpbCI6Imx1ZmZ5QG1pbHppcGRldnMuY29tIiwibmFtZSI6Imx1ZmZ5IiwiYXV0aG9yaXphdGlvbiI6IktJTkciLCJhdmF0YXJVcmwiOiJodHRwczovL2F2YXRhci51cmwvMTIzIiwiaWF0IjoxNTcxMjI2MzYzLCJleHAiOjE1NzEyMjYzNjV9." +
            "w4kRk3C-3Visj-hFlm5fGGqTMr80IZdo7W8ML2IKN8Q";

    private String jwtToken;
    private UserJwtInfoVo userJwtInfoVo;

    @BeforeEach
    void setUp() {
        userJwtInfoVo = new UserJwtInfoVo(
                1L,
                "luffy@milzipdevs.com",
                "luffy",
                "https://avatar.url/123",
                "KING"
        );
        jwtToken = JwtUtils.generateToken(userJwtInfoVo);
        log.debug("token : {}", jwtToken);
    }

    @DisplayName("jwt token 가 userJwtInfoVo 로 생성된 유효한 토큰인지 확인한다.")
    @Test
    void validateToken() {
        assertTrue(JwtUtils.validateToken(jwtToken, userJwtInfoVo));
    }

    @DisplayName("JWT 로부터 UserId 를 성공적으로 받아온다.")
    @Test
    void getUserIdFromToken() {
        assertEquals(JwtUtils.getUserIdFromToken(jwtToken), "1");
    }

    @DisplayName("isTokenExpired 가 만료되지 않은 토큰에 대하여 false 를 반환한다.")
    @Test
    void isTokenExpiredFalse() {
        assertFalse(JwtUtils.isTokenExpired(jwtToken));
    }

    @DisplayName("isTokenExpired 가 만료된 토큰에 대하여 true 를 반환한다.")
    @Test
    void isTokenExpiredTrue() {
        assertTrue(JwtUtils.isTokenExpired(EXPIRED_TOKEN));
    }
}