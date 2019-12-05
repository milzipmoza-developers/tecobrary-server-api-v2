package com.woowacourse.tecobrary.user.command.domain;

import com.woowacourse.tecobrary.user.command.application.NotFoundGithubUserException;
import com.woowacourse.tecobrary.user.common.UserStatic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class UserRepositoryTest implements UserStatic {

    @Autowired
    private UserRepository userRepository;

    @DisplayName("User 객체를 정상적으로 저장한다.")
    @Test
    void save() {
        User savedUser = userRepository.save(TEST_USER);
        assertNotNull(savedUser.getId());
    }

    @DisplayName("getUserByGithubInfo_GithubId가 성공적으로 동작한다.")
    @Test
    void getUserByGithubInfo_GithubId() {
        User savedUser = userRepository.save(TEST_USER);
        assertEquals(
                userRepository.getUserByUserGithubInfoGithubId(TEST_GITHUB_ID)
                        .orElseThrow(NotFoundGithubUserException::new),
                savedUser
        );
    }
}