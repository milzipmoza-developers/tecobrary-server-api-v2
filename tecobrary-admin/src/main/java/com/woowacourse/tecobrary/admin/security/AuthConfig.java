package com.woowacourse.tecobrary.admin.security;

import com.woowacourse.tecobrary.admin.web.AdminUserDto;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class AuthConfig {

    @Bean
    public PrincipalExtractor principalExtractor() {
        return map -> {
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
        };
    }
}
