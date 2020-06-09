package com.woowacourse.tecobrary.security;

import com.woowacourse.tecobrary.domain.user.entity.User;
import com.woowacourse.tecobrary.security.extractor.GithubUserExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserGithubService extends DefaultOAuth2UserService {

    private final GithubUserExtractor githubUserExtractor;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        User user = githubUserExtractor.extract(oAuth2User);

        return GithubUserDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .picture(user.getAvatarUrl())
                .role(user.getAuthorization())
                .build();
    }
}
