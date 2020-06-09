package com.woowacourse.tecobrary.security.extractor;

import com.woowacourse.tecobrary.domain.user.entity.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface UserExtractor {

    String getClientName();

    User extract(OAuth2User oAuth2User);
}
