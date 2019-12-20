package com.woowacourse.tecobrary.user.command.util;

import com.woowacourse.tecobrary.user.command.domain.User;
import com.woowacourse.tecobrary.user.ui.vo.UserJwtInfoVo;

public class UserJwtVoMapper {

    public static UserJwtInfoVo toDomain(final User user) {
        return UserJwtInfoVo.builder()
                .userNo(user.getId())
                .email(user.getUserEmail())
                .name(user.getUserName())
                .avatarUrl(user.getUserAvatarUrl())
                .build();
    }
}
