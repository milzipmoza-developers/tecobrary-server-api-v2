package com.woowacourse.tecobrary.user.ui;

import com.woowacourse.tecobrary.user.application.UserGithubService;
import com.woowacourse.tecobrary.user.utils.JwtUtils;
import com.woowacourse.tecobrary.web.github.api.GithubApiService;
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
public class GithubUserApiController {

    private final GithubApiService githubApiService;
    private final UserGithubService userGithubService;

    @Autowired
    public GithubUserApiController(final GithubApiService githubApiService,
                                   final UserGithubService userGithubService) {
        this.githubApiService = githubApiService;
        this.userGithubService = userGithubService;
    }

    @GetMapping("/auth/user")
    public ResponseEntity tecobraryUserAuthentication(@RequestParam final String code) {
        String githubApiAccessToken = githubApiService.getGithubAccessToken(code);
        UserJwtInfoDto userJwtInfoDto = userGithubService.getUserByGithubInfo(githubApiAccessToken);
        return ResponseEntity.ok(new GithubApiResponseDto(userJwtInfoDto, JwtUtils.generateToken(userJwtInfoDto)));
    }
}
