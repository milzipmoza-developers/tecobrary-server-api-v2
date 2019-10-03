package com.woowacourse.tecobrary.user.ui;

import com.woowacourse.tecobrary.user.command.application.NotFoundGithubUserException;
import com.woowacourse.tecobrary.user.command.application.UserService;
import com.woowacourse.tecobrary.user.command.domain.Authorization;
import com.woowacourse.tecobrary.user.command.domain.User;
import com.woowacourse.tecobrary.user.command.domain.UserAuthorization;
import com.woowacourse.tecobrary.user.infra.util.*;
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

    private final GithubUserApiUtils githubUserApiUtils;
    private final GithubApiRequestClient githubApiRequestClient;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    @Autowired
    public GithubUserApiController(GithubUserApiUtils githubUserApiUtils,
                                   GithubApiRequestClient githubApiRequestClient,
                                   JwtTokenUtil jwtTokenUtil,
                                   UserService userService) {
        this.githubUserApiUtils = githubUserApiUtils;
        this.githubApiRequestClient = githubApiRequestClient;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @GetMapping("/api")
    public ResponseEntity<GithubApiResponseVo> getGithubUserInformation(@RequestParam String code) {
        String githubApiAccessToken = githubUserApiUtils.githubUserApiAccessToken(code);

        User savedUser = savedGithubUserInfo(githubApiAccessToken);

        return ResponseEntity.ok(buildGithubApiResponse(savedUser));
    }

    private User savedGithubUserInfo(String githubApiAccessToken) {
        GithubUserInfoVo githubUserInfoVo = githubUserInfo(githubApiAccessToken);

        try {
            return userService.getByGithubId(githubUserInfoVo.getId());

        } catch (NotFoundGithubUserException e) {
            User user = new User(
                    GithubUserParser.parse(githubUserInfoVo, githubUserEmail(githubApiAccessToken)),
                    new UserAuthorization(Authorization.NONE)
            );
            return userService.save(user);
        }
    }

    private GithubUserInfoVo githubUserInfo(String githubApiAccessToken) {
        return GsonUtils.parseUserInfo(
                githubApiRequestClient.userInfo(githubApiAccessToken));
    }

    private String githubUserEmail(String githubApiAccessToken) {
        return githubUserApiUtils.getPrimaryEmail(
                githubApiRequestClient.userEmail(githubApiAccessToken));
    }

    private GithubApiResponseVo buildGithubApiResponse(User savedUser) {
        ResponseUserVo responseUserVo = new ResponseUserVo(
                savedUser.getUserNo(),
                savedUser.getUserEmail(),
                savedUser.getUserName(),
                savedUser.getUserAvatarUrl(),
                savedUser.getAuthorization()
        );

        String jwtToken = jwtTokenUtil.generateToken(responseUserVo);

        return new GithubApiResponseVo(responseUserVo, jwtToken);
    }
}
