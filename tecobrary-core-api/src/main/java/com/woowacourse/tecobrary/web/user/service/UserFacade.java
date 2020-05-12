package com.woowacourse.tecobrary.web.user.service;

import com.woowacourse.tecobrary.domain.user.entity.User;
import com.woowacourse.tecobrary.domain.user.repository.UserRepository;
import com.woowacourse.tecobrary.web.user.dto.UserInfoResponseDto;
import com.woowacourse.tecobrary.web.user.dto.UserNameDto;
import com.woowacourse.tecobrary.web.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserFacade {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserInfoResponseDto findUserById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        return userConverter.convertInfoResponseDto(user);
    }

    public Long updateUserName(final UserNameDto userNameDto) {
        User user = userRepository.findById(userNameDto.getId())
                .orElseThrow(UserNotFoundException::new);
        user.updateName(userNameDto.getNewName());
        return user.getId();
    }
}
