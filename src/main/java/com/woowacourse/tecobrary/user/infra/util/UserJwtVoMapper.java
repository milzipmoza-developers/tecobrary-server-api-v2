package com.woowacourse.tecobrary.user.infra.util;

import com.woowacourse.tecobrary.user.command.domain.User;
import com.woowacourse.tecobrary.user.ui.vo.UserJwtInfoVo;

public class UserJwtVoMapper {

    public static UserJwtInfoVo map(User user) {
        return new UserJwtInfoVo(
                user.getUserNo(),
                user.getUserEmail(),
                user.getUserName(),
                user.getUserAvatarUrl(),
                user.getAuthorization()
        );
    }
}
