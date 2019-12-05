package com.woowacourse.tecobrary.user.command.application;

import com.woowacourse.tecobrary.user.command.domain.User;
import com.woowacourse.tecobrary.user.command.domain.UserRepository;
import com.woowacourse.tecobrary.user.common.UserStatic;
import com.woowacourse.tecobrary.user.ui.dto.UserAuthDto;
import com.woowacourse.tecobrary.user.ui.dto.UserInfoDto;
import com.woowacourse.tecobrary.user.ui.dto.UserNameDto;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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

    @DisplayName("페이징 후 모든 유저를 조회한다.")
    @Test
    void successfullyFindUsersOnPage() {
        List<User> mockUsers = Arrays.asList(TEST_USER, TEST_USER);
        given(userRepository.findAll(any(PageRequest.class)))
                .willReturn(new PageImpl<>(mockUsers, PageRequest.of(1, 2), 2));
        List<UserInfoDto> users = userService.findUsersOnPage(1, 2);
        assertThat(users).hasSize(2);
    }

    @DisplayName("id로 회원을 조회한다.")
    @Test
    void successfullyFindUserById() {
        given(userRepository.findById(1L)).willReturn(Optional.of(TEST_USER));
        UserInfoDto userInfoDto = userService.findUserById(1L);

        assertEquals(userInfoDto.getGithubId(), TEST_GITHUB_ID);
        assertEquals(userInfoDto.getEmail(), TEST_USER_EMAIL_VALUE);
        assertEquals(userInfoDto.getName(), TEST_USER_NAME_VALUE);
        assertEquals(userInfoDto.getAvatarUrl(), TEST_USER_AVATAR_URL_VALUE);
        assertEquals(userInfoDto.getAuthorization(), TEST_USER_AUTH_VALUE);
    }

    @DisplayName("없는 id 로 findUserById 를 호출하면 NotFoundUserException 이 발생한다.")
    @Test
    void failedGetById() {
        given(userRepository.findById(1L)).willReturn(Optional.empty());

        assertThrows(NotFoundUserException.class, () -> userService.findUserById(1L));
    }

    @DisplayName("회원의 권한을 업데이트한다.")
    @Test
    void successfullyUpdateUserAuth() {
        UserAuthDto userAuthDto = new UserAuthDto(1L, "MANAGER");
        User mockUser = mock(User.class);

        given(userRepository.findById(1L)).willReturn(Optional.of(mockUser));

        userService.updateUserAuth(userAuthDto);

        verify(mockUser).updateAuthorization(userAuthDto.getAuthorization());
    }

    @Test
    void successfullyUpdateUserName() {
        UserNameDto userNameDto = new UserNameDto(1L, "조로");
        User mockUser = mock(User.class);

        given(userRepository.findById(1L)).willReturn(Optional.of(mockUser));

        userService.updateUserName(userNameDto);

        verify(mockUser).updateUserName(userNameDto.getNewName());
    }
}