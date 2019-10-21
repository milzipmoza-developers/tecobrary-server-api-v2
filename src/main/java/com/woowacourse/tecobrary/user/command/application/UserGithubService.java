package com.woowacourse.tecobrary.user.command.application;

import com.woowacourse.tecobrary.user.command.application.api.GithubApiService;
import com.woowacourse.tecobrary.user.command.domain.Authorization;
import com.woowacourse.tecobrary.user.command.domain.User;
import com.woowacourse.tecobrary.user.command.domain.UserAuthorization;
import com.woowacourse.tecobrary.user.command.domain.UserGithubInfo;
import com.woowacourse.tecobrary.user.infra.util.UserGithubInfoMapper;
import com.woowacourse.tecobrary.user.ui.vo.GithubUserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserGithubService {

    private final UserService userService;
    private final GithubApiService githubApiService;

    @Autowired
    public UserGithubService(UserService userService, GithubApiService githubApiService) {
        this.userService = userService;
        this.githubApiService = githubApiService;
    }

    public User getUserByGithubInfo(String githubApiAccessToken) {
        GithubUserInfoVo githubUserInfoVo = githubApiService.getGithubUserInfo(githubApiAccessToken);

        try {
            return userService.findByGithubId(githubUserInfoVo.getId());

        } catch (NotFoundGithubUserException e) {
            UserGithubInfo userGithubInfo = UserGithubInfoMapper.map(githubUserInfoVo,
                    githubApiService.getGithubUserEmail(githubApiAccessToken));
            return userService.save(userGithubInfo, new UserAuthorization(Authorization.NONE));
        }
    }
}
