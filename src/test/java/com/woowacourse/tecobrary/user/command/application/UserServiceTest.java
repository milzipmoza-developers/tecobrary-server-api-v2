package com.woowacourse.tecobrary.user.command.application;

import com.woowacourse.tecobrary.user.command.domain.User;
import com.woowacourse.tecobrary.user.command.domain.UserRepository;
import com.woowacourse.tecobrary.user.common.UserStatic;
import com.woowacourse.tecobrary.user.ui.dto.UserInfoDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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

    @DisplayName("총 회원 수를 조회한다.")
    @Test
    void successfullyCountOfUser() {
        given(userRepository.count()).willReturn(1L);

        long userCount = userService.countOfUser();

        assertThat(userCount).isEqualTo(1L);
    }

    @Test
    void successfullyFindUsersOnPage() {
        List<User> mockUsers = Arrays.asList(TEST_USER, TEST_USER);
        given(userRepository.findAll(any(PageRequest.class)))
                .willReturn(new PageImpl<>(mockUsers, PageRequest.of(1, 2), 2));
        List<UserInfoDto> users = userService.findUsersOnPage(1, 2);
        assertThat(users).hasSize(2);
    }
}