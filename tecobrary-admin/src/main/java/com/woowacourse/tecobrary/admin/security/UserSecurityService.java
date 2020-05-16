package com.woowacourse.tecobrary.admin.security;

import com.woowacourse.tecobrary.admin.security.extractor.GoogleUserExtractor;
import com.woowacourse.tecobrary.admin.web.AdminUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSecurityService extends DefaultOAuth2UserService {

    private final GoogleUserExtractor googleUserExtractor;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        AdminUserDto adminUserDto = googleUserExtractor.extract(oAuth2User);

        return adminUserDto;
    }
}
