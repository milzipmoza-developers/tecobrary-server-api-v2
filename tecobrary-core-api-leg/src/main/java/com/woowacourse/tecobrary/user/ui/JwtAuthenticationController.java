package com.woowacourse.tecobrary.user.ui;

import com.woowacourse.tecobrary.user.application.UserService;
import com.woowacourse.tecobrary.user.utils.JwtUtils;
import com.woowacourse.tecobrary.web.github.dto.GithubApiResponseDto;
import com.woowacourse.tecobrary.web.github.dto.UserJwtInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/api/v2")
public class JwtAuthenticationController {

    private final UserService userService;

    @Autowired
    public JwtAuthenticationController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/token/auth")
    @CrossOrigin(allowedHeaders = {"Authorization"})
    public ResponseEntity authenticate(@RequestHeader("Authorization") final String authorization) {
        String token = authorization.substring(authorization.indexOf(" "));
        String userNo = JwtUtils.getUserIdFromToken(token);
        UserJwtInfoDto userJwtInfoDto = userService.findUserJwtInfoByUserNo(userNo);
        return ResponseEntity.ok(new GithubApiResponseDto(userJwtInfoDto, token));
    }
}
