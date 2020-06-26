package com.woowacourse.tecobrary.web.oauth.service;

import com.woowacourse.tecobrary.domain.user.entity.User;
import com.woowacourse.tecobrary.domain.user.repository.UserRepository;
import com.woowacourse.tecobrary.web.github.api.GithubApiService;
import com.woowacourse.tecobrary.web.github.dto.GithubUserInfoDto;
import com.woowacourse.tecobrary.web.github.dto.UserJwtInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GithubUserService {

    private final UserRepository userRepository;
    private final GithubApiService githubApiService;

    public UserJwtInfoDto getUserByGithubInfo(String githubAccessToken) {
        GithubUserInfoDto githubUserInfoDto = githubApiService.getGithubUserInfo(githubAccessToken);

        User user = userRepository.findByGithubId(githubUserInfoDto.getId())
                .orElse(newGithubUser(githubAccessToken, githubUserInfoDto));

        return UserJwtInfoDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .avatarUrl(user.getAvatarUrl())
                .authorization(user.getAuthorization())
                .build();
    }

    private User newGithubUser(String githubAccessToken, GithubUserInfoDto githubUser) {
        String email = githubApiService.getGithubUserEmail(githubAccessToken);
        User user = User.builder()
                .githubId(githubUser.getId())
                .email(email)
                .avatarUrl(githubUser.getAvatar_url())
                .name(githubUser.getName())
                .authorization("NONE")
                .build();
        return userRepository.save(user);
    }
}
