package com.woowacourse.tecobrary.web.oauth;

import com.woowacourse.tecobrary.common.dto.CoreApiResponse;
import com.woowacourse.tecobrary.web.github.api.GithubApiService;
import com.woowacourse.tecobrary.web.github.dto.GithubApiResponseDto;
import com.woowacourse.tecobrary.web.github.dto.UserJwtInfoDto;
import com.woowacourse.tecobrary.web.github.utils.GithubUserApiUrlBuilder;
import com.woowacourse.tecobrary.web.oauth.jwt.JwtGenerator;
import com.woowacourse.tecobrary.web.oauth.service.GithubUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@Slf4j
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class GithubOAuthController {

    private final GithubUserApiUrlBuilder githubUserApiUrlBuilder;
    private final GithubApiService githubApiService;
    private final GithubUserService githubUserService;
    private final JwtGenerator jwtGenerator;

    @GetMapping("/login/github/oauth2")
    public RedirectView github() {
        return new RedirectView(githubUserApiUrlBuilder.oauth());
    }

    @GetMapping("/login/tecobrary")
    public CoreApiResponse<GithubApiResponseDto> userAuth(@RequestParam String code) {
        String accessToken = githubApiService.getGithubAccessToken(code);
        UserJwtInfoDto userJwtInfoDto = githubUserService.getUserByGithubInfo(accessToken);
        return CoreApiResponse.success(GithubApiResponseDto.builder()
                .user(userJwtInfoDto)
                .token(jwtGenerator.generateToken(userJwtInfoDto))
                .build());
    }
}
