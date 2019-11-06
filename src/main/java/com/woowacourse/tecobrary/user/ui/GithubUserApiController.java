package com.woowacourse.tecobrary.user.ui;

import com.woowacourse.tecobrary.user.command.application.UserGithubService;
import com.woowacourse.tecobrary.user.command.application.api.GithubApiService;
import com.woowacourse.tecobrary.user.command.domain.User;
import com.woowacourse.tecobrary.user.infra.util.JwtUtils;
import com.woowacourse.tecobrary.user.infra.util.UserJwtVoMapper;
import com.woowacourse.tecobrary.user.ui.vo.GithubApiResponseVo;
import com.woowacourse.tecobrary.user.ui.vo.UserJwtInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/tecobrary")
public class GithubUserApiController {

    private final JwtUtils jwtUtils;
    private final GithubApiService githubApiService;
    private final UserGithubService userGithubService;

    @Autowired
    public GithubUserApiController(JwtUtils jwtUtils,
                                   GithubApiService githubApiService,
                                   UserGithubService userGithubService) {
        this.jwtUtils = jwtUtils;
        this.githubApiService = githubApiService;
        this.userGithubService = userGithubService;
    }

    @GetMapping("/auth")
    public ResponseEntity<GithubApiResponseVo> tecobraryUserAuthentication(@RequestParam String code) {
        String githubApiAccessToken = githubApiService.getGithubAccessToken(code);
        User savedUser = userGithubService.getUserByGithubInfo(githubApiAccessToken);
        UserJwtInfoVo userJwtInfoVo = UserJwtVoMapper.map(savedUser);
        return ResponseEntity.ok(new GithubApiResponseVo(
                userJwtInfoVo, jwtUtils.generateToken(userJwtInfoVo)));
    }
}
