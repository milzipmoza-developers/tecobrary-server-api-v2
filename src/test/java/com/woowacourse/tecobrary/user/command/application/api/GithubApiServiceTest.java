package com.woowacourse.tecobrary.user.command.application.api;

import com.woowacourse.tecobrary.user.common.GithubApiStatic;
import com.woowacourse.tecobrary.user.ui.vo.GithubUserInfoVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class GithubApiServiceTest implements GithubApiStatic {

    private static final String VIRTUAL_ACCESS_TOKEN = "access token is this";
    private static final String GITHUB_API_USERS_RETURN = "{\n" +
            "  \"login\": \"octocat\",\n" +
            "  \"id\": 1,\n" +
            "  \"node_id\": \"MDQ6VXNlcjE=\",\n" +
            "  \"avatar_url\": \"https://github.com/images/error/octocat_happy.gif\",\n" +
            "  \"gravatar_id\": \"\",\n" +
            "  \"url\": \"https://api.github.com/users/octocat\",\n" +
            "  \"html_url\": \"https://github.com/octocat\",\n" +
            "  \"followers_url\": \"https://api.github.com/users/octocat/followers\",\n" +
            "  \"following_url\": \"https://api.github.com/users/octocat/following{/other_user}\",\n" +
            "  \"gists_url\": \"https://api.github.com/users/octocat/gists{/gist_id}\",\n" +
            "  \"starred_url\": \"https://api.github.com/users/octocat/starred{/owner}{/repo}\",\n" +
            "  \"subscriptions_url\": \"https://api.github.com/users/octocat/subscriptions\",\n" +
            "  \"organizations_url\": \"https://api.github.com/users/octocat/orgs\",\n" +
            "  \"repos_url\": \"https://api.github.com/users/octocat/repos\",\n" +
            "  \"events_url\": \"https://api.github.com/users/octocat/events{/privacy}\",\n" +
            "  \"received_events_url\": \"https://api.github.com/users/octocat/received_events\",\n" +
            "  \"type\": \"User\",\n" +
            "  \"site_admin\": false,\n" +
            "  \"name\": \"monalisa octocat\",\n" +
            "  \"company\": \"GitHub\",\n" +
            "  \"blog\": \"https://github.com/blog\",\n" +
            "  \"location\": \"San Francisco\",\n" +
            "  \"email\": \"octocat@github.com\",\n" +
            "  \"hireable\": false,\n" +
            "  \"bio\": \"There once was...\",\n" +
            "  \"public_repos\": 2,\n" +
            "  \"public_gists\": 1,\n" +
            "  \"followers\": 20,\n" +
            "  \"following\": 0,\n" +
            "  \"created_at\": \"2008-01-14T04:33:35Z\",\n" +
            "  \"updated_at\": \"2008-01-14T04:33:35Z\"\n" +
            "}";
    private static final String GITHUB_API_EMAIL_RETURN = "[\n" +
            "  {\n" +
            "    \"email\": \"octocat@github.com\",\n" +
            "    \"verified\": true,\n" +
            "    \"primary\": true,\n" +
            "    \"visibility\": \"public\"\n" +
            "  }\n" +
            "]";

    @InjectMocks
    private GithubApiService githubApiService;

    @Mock
    private GithubApi githubApi;

    @Mock
    private GithubApiClient githubApiClient;

    @DisplayName("getGithubAccessToken 메서드가 정상적으로 동작한다.")
    @Test
    void getGithubAccessToken() {
        given(githubApi.githubUserApiAccessToken(CODE_VALUE)).willReturn(VIRTUAL_ACCESS_TOKEN);

        assertEquals(githubApiService.getGithubAccessToken(CODE_VALUE), VIRTUAL_ACCESS_TOKEN);
    }

    @DisplayName("getGithubUserInfo 메서드가 정상적으로 동작한다.")
    @Test
    void githubUserInfo() {
        given(githubApiClient.userInfo(VIRTUAL_ACCESS_TOKEN)).willReturn(GITHUB_API_USERS_RETURN);

        GithubUserInfoVo expected = new GithubUserInfoVo("1", "https://github.com/images/error/octocat_happy.gif", "octocat");

        assertEquals(githubApiService.getGithubUserInfo(VIRTUAL_ACCESS_TOKEN), expected);
    }

    @DisplayName("getGithubUserEmail 메서드가 정상적으로 동작한다.")
    @Test
    void githubUserEmail() {
        given(githubApiClient.userEmail(VIRTUAL_ACCESS_TOKEN)).willReturn(GITHUB_API_EMAIL_RETURN);
        given(githubApi.getPrimaryEmail(GITHUB_API_EMAIL_RETURN)).willReturn("octocat@github.com");

        String expected = "octocat@github.com";

        assertEquals(githubApiService.getGithubUserEmail(VIRTUAL_ACCESS_TOKEN), expected);
    }
}