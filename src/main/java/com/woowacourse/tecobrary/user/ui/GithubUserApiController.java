package com.woowacourse.tecobrary.user.ui;

import com.woowacourse.tecobrary.user.command.application.NotFoundGithubUserException;
import com.woowacourse.tecobrary.user.command.application.UserService;
import com.woowacourse.tecobrary.user.command.application.api.GithubApiService;
import com.woowacourse.tecobrary.user.command.domain.Authorization;
import com.woowacourse.tecobrary.user.command.domain.User;
import com.woowacourse.tecobrary.user.command.domain.UserAuthorization;
import com.woowacourse.tecobrary.user.infra.util.GithubUserMapper;
import com.woowacourse.tecobrary.user.infra.util.JwtUtil;
import com.woowacourse.tecobrary.user.ui.vo.GithubApiResponseVo;
import com.woowacourse.tecobrary.user.ui.vo.GithubUserInfoVo;
import com.woowacourse.tecobrary.user.ui.vo.ResponseUserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/github/user")
public class GithubUserApiController {

    private final JwtUtil jwtUtil;
    private final GithubApiService githubApiService;
    private final UserService userService;

    @Autowired
    public GithubUserApiController(JwtUtil jwtUtil,
                                   GithubApiService githubApiService,
                                   UserService userService) {
        this.jwtUtil = jwtUtil;
        this.githubApiService = githubApiService;
        this.userService = userService;
    }

    @GetMapping("/api")
    public ResponseEntity<GithubApiResponseVo> getGithubUserInformation(@RequestParam String code) {
        String githubApiAccessToken = githubApiService.getGithubAccessToken(code);
        User savedUser = savedGithubUserInfo(githubApiAccessToken);
        return ResponseEntity.ok(buildGithubApiResponse(savedUser));
    }

    private User savedGithubUserInfo(String githubApiAccessToken) {
        GithubUserInfoVo githubUserInfoVo = githubApiService.githubUserInfo(githubApiAccessToken);

        try {
            return userService.getByGithubId(githubUserInfoVo.getId());

        } catch (NotFoundGithubUserException e) {
            User user = new User(
                    GithubUserMapper.map(githubUserInfoVo,
                            githubApiService.githubUserEmail(githubApiAccessToken)),
                    new UserAuthorization(Authorization.NONE)
            );
            return userService.save(user);
        }
    }

    private GithubApiResponseVo buildGithubApiResponse(User savedUser) {
        ResponseUserVo responseUserVo = new ResponseUserVo(
                savedUser.getUserNo(),
                savedUser.getUserEmail(),
                savedUser.getUserName(),
                savedUser.getUserAvatarUrl(),
                savedUser.getAuthorization()
        );

        String jwtToken = jwtUtil.generateToken(responseUserVo);

        return new GithubApiResponseVo(responseUserVo, jwtToken);
    }
}
