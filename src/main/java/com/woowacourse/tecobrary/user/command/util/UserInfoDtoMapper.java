/*
 * @(#) UserInfoDtoMapper.java
 *
 * v 1.0.0
 *
 * 2019.12.02
 *
 * Copyright (c) 2019 woowacourse, thedevluffy, gch01410, LeeYounghyeon
 * All rights reserved
 */

package com.woowacourse.tecobrary.user.command.util;

import com.woowacourse.tecobrary.user.command.domain.User;
import com.woowacourse.tecobrary.user.ui.dto.UserInfoDto;

public class UserInfoDtoMapper {

    public static UserInfoDto toDto(final User user) {
        return UserInfoDto.builder()
                .githubId(user.getGithubId())
                .email(user.getUserEmail())
                .name(user.getUserName())
                .avatarUrl(user.getUserAvatarUrl())
                .authorization(user.getAuthorization())
                .build();
    }
}
