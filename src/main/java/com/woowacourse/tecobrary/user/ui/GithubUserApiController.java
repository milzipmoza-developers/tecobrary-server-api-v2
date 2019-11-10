package com.woowacourse.tecobrary.user.ui;

import com.woowacourse.tecobrary.user.command.application.UserGithubService;
import com.woowacourse.tecobrary.user.command.application.api.GithubApiService;
import com.woowacourse.tecobrary.user.infra.util.JwtUtils;
import com.woowacourse.tecobrary.user.ui.vo.GithubApiResponseVo;
import com.woowacourse.tecobrary.user.ui.vo.UserJwtInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/")
public class GithubUserApiController {

    private final GithubApiService githubApiService;
    private final UserGithubService userGithubService;

    @Autowired
    public GithubUserApiController(GithubApiService githubApiService,
                                   UserGithubService userGithubService) {
        this.githubApiService = githubApiService;
        this.userGithubService = userGithubService;
    }

    @GetMapping("/auth/user")
    public ResponseEntity tecobraryUserAuthentication(@RequestParam String code) {
        String githubApiAccessToken = githubApiService.getGithubAccessToken(code);
        UserJwtInfoVo userJwtInfoVo = userGithubService.getUserByGithubInfo(githubApiAccessToken);
        return ResponseEntity.ok(new GithubApiResponseVo(userJwtInfoVo, JwtUtils.generateToken(userJwtInfoVo)));
    }
}
