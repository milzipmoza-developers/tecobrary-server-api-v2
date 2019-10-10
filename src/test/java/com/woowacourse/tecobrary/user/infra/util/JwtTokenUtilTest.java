package com.woowacourse.tecobrary.user.infra.util;

import com.woowacourse.tecobrary.user.ui.vo.ResponseUserVo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class JwtTokenUtilTest {

    private static final Logger log = LoggerFactory.getLogger(JwtTokenUtilTest.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Test
    void name() {
        ResponseUserVo responseUserVo = new ResponseUserVo(
                1L,
                "luffy@milzipdevs.com",
                "luffy",
                "https://avatar.url/123",
                "KING"
        );
        String jwtToken = jwtTokenUtil.generateToken(responseUserVo);
        log.debug("generated jwt token : {}", jwtToken);
    }
}