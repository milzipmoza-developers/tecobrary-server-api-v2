package com.woowacourse.tecobrary.user.common;

import com.woowacourse.tecobrary.common.model.Email;
import com.woowacourse.tecobrary.common.model.HttpsUrl;
import com.woowacourse.tecobrary.user.command.domain.Authorization;
import com.woowacourse.tecobrary.user.command.domain.User;
import com.woowacourse.tecobrary.user.command.domain.UserAuthorization;
import com.woowacourse.tecobrary.user.command.domain.UserGithubInfo;
import com.woowacourse.tecobrary.user.command.domain.UserName;

public interface UserStatics {
    String TEST_GITHUB_ID = "100";
    UserName TEST_USER_NAME = new UserName("thedevluffy");
    Email TEST_USER_EMAIL = new Email("thedevluffy@gmail.com");
    HttpsUrl TEST_HTTPS_URL = new HttpsUrl("https://avatars0.githubusercontent.com/u/52121827?s=460&v=4");

    UserGithubInfo TEST_USER_GITHUB_INFO = new UserGithubInfo(
            TEST_GITHUB_ID,
            TEST_USER_NAME,
            TEST_USER_EMAIL,
            TEST_HTTPS_URL
    );

    UserAuthorization TEST_USER_AUTHORIZATION = new UserAuthorization(Authorization.USER);

    User TEST_USER = new User(TEST_USER_GITHUB_INFO, TEST_USER_AUTHORIZATION);
}
