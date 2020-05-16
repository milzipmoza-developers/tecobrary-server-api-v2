package com.woowacourse.tecobrary.admin.security.extractor;

import com.woowacourse.tecobrary.admin.web.AdminUserDto;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface UserExtractor {

    String getClientName();

    AdminUserDto extract(OAuth2User oAuth2User);
}
