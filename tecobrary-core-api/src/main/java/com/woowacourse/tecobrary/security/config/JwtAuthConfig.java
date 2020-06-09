package com.woowacourse.tecobrary.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Getter
@Setter
@ConfigurationProperties(prefix = "jwt-auth")
public class JwtAuthConfig {

    private String secret;
    private Long expire;
    private List<String> redirectUri;
}
