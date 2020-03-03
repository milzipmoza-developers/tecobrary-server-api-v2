/*
 * @(#) UserGithubService.java
 *
 * v 1.0.0
 *
 * 2019.11.29
 *
 * Copyright (c) 2019 woowacourse, thedevluffy
 * All rights reserved
 */

package com.woowacourse.tecobrary.user.application;

import com.woowacourse.tecobrary.github.api.GithubApiService;
import com.woowacourse.tecobrary.github.dto.GithubUserInfoDto;
import com.woowacourse.tecobrary.user.util.UserGithubInfoMapper;
import com.woowacourse.tecobrary.user.util.UserJwtDtoConverter;
import com.woowacourse.tecobrary.user.domain.User;
import com.woowacourse.tecobrary.user.domain.UserGithubInfo;
import com.woowacourse.tecobrary.github.dto.UserJwtInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserGithubService {

    private final UserService userService;
    private final GithubApiService githubApiService;

    @Autowired
    public UserGithubService(final UserService userService, final GithubApiService githubApiService) {
        this.userService = userService;
        this.githubApiService = githubApiService;
    }

    public UserJwtInfoDto getUserByGithubInfo(final String githubApiAccessToken) {
        GithubUserInfoDto githubUserInfoVo = githubApiService.getGithubUserInfo(githubApiAccessToken);
        try {
            return UserJwtDtoConverter.convert(userService.findByGithubId(githubUserInfoVo.getId()));
        } catch (NotFoundGithubUserException e) {
            return UserJwtDtoConverter.convert(getNewUserAfterSave(githubApiAccessToken, githubUserInfoVo));
        }
    }

    private User getNewUserAfterSave(final String githubApiAccessToken, final GithubUserInfoDto githubUserInfoVo) {
        UserGithubInfo userGithubInfo = UserGithubInfoMapper.toDomain(githubUserInfoVo,
                githubApiService.getGithubUserEmail(githubApiAccessToken));
        return userService.save(userGithubInfo);
    }
}
