package com.woowacourse.tecobrary.user.infra.util;

import com.woowacourse.tecobrary.user.ui.vo.UserResponseVo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class JwtUtilTest {

    private static final Logger log = LoggerFactory.getLogger(JwtUtilTest.class);

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    void name() {
        UserResponseVo userResponseVo = new UserResponseVo(
                1L,
                "luffy@milzipdevs.com",
                "luffy",
                "https://avatar.url/123",
                "KING"
        );
        String jwtToken = jwtUtil.generateToken(userResponseVo);
        log.debug("generated jwt token : {}", jwtToken);
    }
}