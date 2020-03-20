package user.common;

import com.woowacourse.tecobrary.common.domain.Email;
import com.woowacourse.tecobrary.common.domain.HttpsUrl;
import com.woowacourse.tecobrary.github.dto.GithubUserInfoDto;
import com.woowacourse.tecobrary.github.dto.UserJwtInfoDto;
import com.woowacourse.tecobrary.user.entity.*;

public interface UserStatic {
    Long TEST_USER_NO = 2L;
    String TEST_GITHUB_ID = "100";
    String TEST_USER_NAME_VALUE = "thedevluffy";
    String TEST_USER_EMAIL_VALUE = "thedevluffy@gmail.com";
    String TEST_USER_AVATAR_URL_VALUE = "https://avatars0.githubusercontent.com/u/52121827?s=460&v=4";
    String TEST_USER_AUTH_VALUE = "NONE";

    Long SAVED_USER_NO = 1L;
    String SAVED_GITHUB_ID_AT_ID_01 = "12345678";
    String SAVED_USER_NAME_VALUE_AT_ID_01 = "루피";
    String SAVED_USER_EMAIL_VALUE_AT_ID_01 = "erasede@tecobrary.com";
    String SAVED_USER_AVATAR_URL_VALUE_AT_ID_01 = "https://avatars3.githubusercontent.com/u/32266963?v=4";
    String SAVED_USER_AUTH_VALUE_AT_ID_01 = "KING";

    String SAVED_GITHUB_ID_AT_ID_02 = "91011121";
    String SAVED_USER_EMAIL_VALUE_AT_ID_02 = "qweqwew@tecobrary.com";
    String SAVED_USER_NAME_VALUE_AT_ID_02 = "던던";
    String SAVED_USER_AVATAR_URL_VALUE_AT_ID_02 = "https://avatars3.githubusercontent.com/u/32266963?v=4";
    String SAVED_USER_AUTH_VALUE_AT_ID_02 = "USER";

    UserName TEST_USER_NAME = new UserName(TEST_USER_NAME_VALUE);
    Email TEST_USER_EMAIL = new Email(TEST_USER_EMAIL_VALUE);
    HttpsUrl TEST_HTTPS_URL = new HttpsUrl(TEST_USER_AVATAR_URL_VALUE);

    UserName SAVED_USER_NAME = new UserName(SAVED_USER_NAME_VALUE_AT_ID_01);
    Email SAVED_USER_EMAIL = new Email(SAVED_USER_EMAIL_VALUE_AT_ID_01);
    HttpsUrl SAVED_HTTPS_URL = new HttpsUrl(SAVED_USER_AVATAR_URL_VALUE_AT_ID_01);

    UserGithubInfo TEST_USER_GITHUB_INFO = new UserGithubInfo(
            TEST_GITHUB_ID,
            TEST_USER_NAME,
            TEST_USER_EMAIL,
            TEST_HTTPS_URL
    );

    UserGithubInfo SAVED_USER_GITHUB_INFO = new UserGithubInfo(
            SAVED_GITHUB_ID_AT_ID_01,
            SAVED_USER_NAME,
            SAVED_USER_EMAIL,
            SAVED_HTTPS_URL
    );

    GithubUserInfoDto TEST_GITHUB_USER_INFO_VO = new GithubUserInfoDto(
            TEST_GITHUB_ID,
            TEST_USER_AVATAR_URL_VALUE,
            TEST_USER_NAME_VALUE
    );

    GithubUserInfoDto SAVED_GITHUB_USER_INFO_VO = new GithubUserInfoDto(
            SAVED_GITHUB_ID_AT_ID_01,
            SAVED_USER_AVATAR_URL_VALUE_AT_ID_01,
            SAVED_USER_NAME_VALUE_AT_ID_01
    );

    UserAuthorization TEST_USER_AUTHORIZATION = new UserAuthorization(Authorization.NONE);

    UserAuthorization SAVED_USER_AUTHORIZATION = new UserAuthorization(Authorization.KING);

    User TEST_USER = new User(TEST_USER_GITHUB_INFO, TEST_USER_AUTHORIZATION);

    User SAVED_USER = new User(SAVED_USER_GITHUB_INFO, SAVED_USER_AUTHORIZATION);

    UserJwtInfoDto SAVED_USER_JWT_INFO_VO = UserJwtInfoDto.builder()
            .id(SAVED_USER_NO)
            .email(SAVED_USER_EMAIL_VALUE_AT_ID_01)
            .name(SAVED_USER_NAME_VALUE_AT_ID_01)
            .avatarUrl(SAVED_USER_AVATAR_URL_VALUE_AT_ID_01)
            .authorization(SAVED_USER_AUTH_VALUE_AT_ID_01)
            .build();

    UserJwtInfoDto TEST_USER_JWT_INFO_VO = UserJwtInfoDto.builder()
            .id(TEST_USER_NO)
            .email(TEST_USER_EMAIL_VALUE)
            .name(TEST_USER_NAME_VALUE)
            .avatarUrl(TEST_USER_AVATAR_URL_VALUE)
            .authorization(TEST_USER_AUTH_VALUE)
            .build();
}
