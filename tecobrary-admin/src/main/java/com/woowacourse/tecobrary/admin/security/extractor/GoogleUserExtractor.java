package com.woowacourse.tecobrary.admin.security.extractor;

import com.woowacourse.tecobrary.admin.web.AdminUserDto;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class GoogleUserExtractor implements UserExtractor {

    @Override
    public String getClientName() {
        return "google";
    }

    @Override
    public AdminUserDto extract(OAuth2User oAuth2User) {
        Map<String, Object> map = oAuth2User.getAttributes();

        String email = Optional.ofNullable(map.get("email"))
                .map(String::valueOf)
                .orElseThrow(() -> new IllegalArgumentException("이메일이 없습니다."));

        String name = Optional.ofNullable(map.get("name"))
                .map(String::valueOf)
                .orElseThrow(() -> new IllegalArgumentException("이름이 없습니다."));

        String picture = Optional.ofNullable(map.get("picture"))
                .map(String::valueOf)
                .orElse("null");

        return AdminUserDto.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .build();
    }
}
