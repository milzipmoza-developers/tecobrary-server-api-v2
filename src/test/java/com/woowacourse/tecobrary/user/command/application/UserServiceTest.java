package com.woowacourse.tecobrary.user.command.application;

import com.woowacourse.tecobrary.user.command.domain.UserRepository;
import com.woowacourse.tecobrary.user.common.UserStatic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class UserServiceTest implements UserStatic {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @DisplayName("findByGithubId 가 성공적으로 작동한다.")
    @Test
    void successGetByGithubId() {
        given(userRepository.getUserByUserGithubInfoGithubId(TEST_GITHUB_ID)).willReturn(Optional.of(TEST_USER));

        assertEquals(userService.findByGithubId(TEST_GITHUB_ID), TEST_USER);
    }

    @DisplayName("없는 githubId 로 findByGithubId 를 호출하면 NotFoundGithubUserException 이 발생한다.")
    @Test
    void failedGetByGithubId() {
        given(userRepository.getUserByUserGithubInfoGithubId(TEST_GITHUB_ID)).willReturn(Optional.of(TEST_USER));

        assertThrows(NotFoundGithubUserException.class, () -> userService.findByGithubId("123"));
    }
}