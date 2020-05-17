package com.woowacourse.tecobrary.admin.security.extractor;

import com.woowacourse.tecobrary.admin.web.AdminUserDto;
import com.woowacourse.tecobrary.domain.admin.entity.Admin;
import com.woowacourse.tecobrary.domain.admin.repository.AdminRepository;
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
public class GoogleUserExtractor implements UserExtractor {

    private final AdminRepository adminRepository;

    @Override
    public String getClientName() {
        return "google";
    }

    @Override
    public Admin extract(OAuth2User oAuth2User) {
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

        log.info("[GoogleUserExtractor] email={}, name={} 로그인 시도", email, name);

        Admin admin = adminRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("등록된 유저가 아닙니다."));

        return admin.updateUserInfo(name, picture);
    }
}
