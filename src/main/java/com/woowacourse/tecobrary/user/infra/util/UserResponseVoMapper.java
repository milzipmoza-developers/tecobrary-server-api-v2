package com.woowacourse.tecobrary.user.infra.util;

import com.woowacourse.tecobrary.user.command.domain.User;
import com.woowacourse.tecobrary.user.ui.vo.UserResponseVo;

public class UserResponseVoMapper {

    public static UserResponseVo map(User user) {
        return new UserResponseVo(
                user.getUserNo(),
                user.getUserEmail(),
                user.getUserName(),
                user.getUserAvatarUrl(),
                user.getAuthorization()
        );
    }
}
