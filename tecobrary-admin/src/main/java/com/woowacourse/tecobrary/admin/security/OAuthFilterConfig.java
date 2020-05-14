package com.woowacourse.tecobrary.admin.security;

import com.woowacourse.tecobrary.admin.handler.AdminAuthSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateFactory;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;

@Configuration
@RequiredArgsConstructor
public class OAuthFilterConfig {

    private final UserInfoTokenServices userInfoTokenServices;
    private final UserInfoRestTemplateFactory userInfoRestTemplateFactory;

    @Bean
    public OAuth2ClientAuthenticationProcessingFilter ssoFilter(AdminAuthSuccessHandler successHandler) {
        OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter("/login");
        filter.setRestTemplate(userInfoRestTemplateFactory.getUserInfoRestTemplate());
        filter.setTokenServices(userInfoTokenServices);
        filter.setAuthenticationSuccessHandler(successHandler);
        return filter;
    }
}
