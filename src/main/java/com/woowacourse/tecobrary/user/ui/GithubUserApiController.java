package com.woowacourse.tecobrary.user.ui;

import com.woowacourse.tecobrary.user.command.application.UserService;
import com.woowacourse.tecobrary.user.command.domain.*;
import com.woowacourse.tecobrary.user.infra.dto.GithubUserInfoDto;
import com.woowacourse.tecobrary.user.infra.util.GithubApiRequestClient;
import com.woowacourse.tecobrary.user.infra.util.GithubUserApiUtils;
import com.woowacourse.tecobrary.user.infra.util.GsonUtils;
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

    private final GithubUserApiUtils githubUserApiUtils;
    private final GithubApiRequestClient githubApiRequestClient;
    private final UserService userService;

    @Autowired
    public GithubUserApiController(GithubUserApiUtils githubUserApiUtils,
                                   GithubApiRequestClient githubApiRequestClient,
                                   UserService userService) {
        this.githubUserApiUtils = githubUserApiUtils;
        this.githubApiRequestClient = githubApiRequestClient;
        this.userService = userService;
    }

    @GetMapping("/api")
    public ResponseEntity<UserResponseVo> getGithubUserInformation(@RequestParam String code) {
        String githubApiAccessToken = githubUserApiUtils.githubUserApiAccessToken(code);

        User user = new User(
                composeUserInfo(githubUserInfo(githubApiAccessToken), githubUserEmail(githubApiAccessToken)),
                new UserAuthorization(Authorization.NONE)
        );

        User savedUser = userService.save(user);

        // TODO
        //  jwt 만들어 응답 responseVo 에 넣어 response 하는 작업이 필요함,
        //  Doamin 값들을 직접 꺼내오는 것에 대한 고찰이 필요
        UserResponseVo userResponseVo =  new UserResponseVo(
                savedUser.getUserEmail(),
                savedUser.getUserName(),
                savedUser.getUserAvatarUrl(),
                savedUser.getAuthorization()
        );

        return ResponseEntity.ok(userResponseVo);
    }

    private UserGithubInfo composeUserInfo(GithubUserInfoVo githubUserInfoVo, String primaryEmail) {
        GithubUserInfoDto githubUserInfo = new GithubUserInfoDto(
                githubUserInfoVo.getId(),
                githubUserInfoVo.getName(),
                primaryEmail,
                githubUserInfoVo.getAvatar_url()
        );

        return new UserGithubInfo(
                githubUserInfo.getGithubId(),
                githubUserInfo.getName(),
                githubUserInfo.getEmail(),
                githubUserInfo.getAvatarUrl()
        );
    }

    private GithubUserInfoVo githubUserInfo(String githubApiAccessToken) {
        return GsonUtils.parseUserInfo(
                githubApiRequestClient.userInfo(githubApiAccessToken));
    }

    private String githubUserEmail(String githubApiAccessToken) {
        return githubUserApiUtils.getPrimaryEmail(
                githubApiRequestClient.userEmail(githubApiAccessToken));
    }
}
