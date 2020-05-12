package com.woowacourse.tecobrary.web.user.service;

import com.woowacourse.tecobrary.domain.user.entity.User;
import com.woowacourse.tecobrary.web.user.dto.UserInfoResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserInfoResponseDto convertInfoResponseDto(User user) {
        return UserInfoResponseDto.builder()
                .githubId(user.getGithubId())
                .email(user.getEmail())
                .name(user.getName())
                .avatarUrl(user.getAvatarUrl())
                .authorization(user.getAuthorization())
                .build();
    }
}
