package com.woowacourse.tecobrary.admin.security.extractor;

import com.woowacourse.tecobrary.domain.admin.entity.Admin;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface UserExtractor {

    String getClientName();

    Admin extract(OAuth2User oAuth2User);
}
