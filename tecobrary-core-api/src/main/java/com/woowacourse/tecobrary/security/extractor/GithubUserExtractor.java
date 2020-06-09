package com.woowacourse.tecobrary.security.extractor;

import com.woowacourse.tecobrary.domain.user.entity.User;
import com.woowacourse.tecobrary.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GithubUserExtractor implements UserExtractor {

    private final UserRepository userRepository;

    @Override
    public String getClientName() {
        return "github";
    }

    @Override
    public User extract(OAuth2User oAuth2User) {
        log.info("{}", oAuth2User);

        Map<String, Object> attributes = oAuth2User.getAttributes();

        String githubId = Optional.of(attributes.get("id"))
                .map(String::valueOf)
                .orElse(null);

        String avatarUrl = Optional.of(attributes.get("avatar_url"))
                .map(String::valueOf)
                .orElse(null);

        String name = Optional.of(attributes.get("name"))
                .map(String::valueOf)
                .orElse(null);

        String email = Optional.of(attributes.get("email"))
                .map(String::valueOf)
                .orElse(null);

        User user = userRepository.findByGithubId(githubId)
                .orElse(User.builder()
                        .githubId(githubId)
                        .email(email)
                        .name(name)
                        .avatarUrl(avatarUrl)
                        .authorization("NONE")
                        .build());

        user.updateGithubInfo(name, email);
        return userRepository.save(user);
    }
}
