package com.woowacourse.tecobrary.user.ui;

import com.woowacourse.tecobrary.user.command.application.NotFoundGithubUserException;
import com.woowacourse.tecobrary.user.command.application.UserService;
import com.woowacourse.tecobrary.user.command.application.api.GithubApiService;
import com.woowacourse.tecobrary.user.command.domain.Authorization;
import com.woowacourse.tecobrary.user.command.domain.User;
import com.woowacourse.tecobrary.user.command.domain.UserAuthorization;
import com.woowacourse.tecobrary.user.infra.util.JwtUtil;
import com.woowacourse.tecobrary.user.infra.util.UserGithubInfoMapper;
import com.woowacourse.tecobrary.user.infra.util.UserResponseVoMapper;
import com.woowacourse.tecobrary.user.ui.vo.GithubApiResponseVo;
import com.woowacourse.tecobrary.user.ui.vo.GithubUserInfoVo;
import com.woowacourse.tecobrary.user.ui.vo.UserResponseVo;
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
        UserResponseVo userResponseVo = UserResponseVoMapper.map(savedUser);
        return ResponseEntity.ok(new GithubApiResponseVo(
                userResponseVo, jwtUtil.generateToken(userResponseVo)));
    }

    private User savedGithubUserInfo(String githubApiAccessToken) {
        GithubUserInfoVo githubUserInfoVo = githubApiService.githubUserInfo(githubApiAccessToken);

        try {
            return userService.findByGithubId(githubUserInfoVo.getId());

        } catch (NotFoundGithubUserException e) {
            User user = new User(
                    UserGithubInfoMapper.map(githubUserInfoVo,
                            githubApiService.githubUserEmail(githubApiAccessToken)),
                    new UserAuthorization(Authorization.NONE)
            );
            return userService.save(user);
        }
    }
}
