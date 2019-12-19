/*
 * @(#) UserService.java
 *
 * v 1.0.0
 *
 * 2019.11.29
 *
 * Copyright (c) 2019 woowacourse, thedevluffy
 * All rights reserved
 */

package com.woowacourse.tecobrary.user.command.application;

import com.woowacourse.tecobrary.user.command.domain.*;
import com.woowacourse.tecobrary.user.command.util.UserInfoDtoMapper;
import com.woowacourse.tecobrary.user.command.util.UserJwtVoMapper;
import com.woowacourse.tecobrary.user.ui.dto.UserAuthDto;
import com.woowacourse.tecobrary.user.ui.dto.UserInfoDto;
import com.woowacourse.tecobrary.user.ui.dto.UserNameDto;
import com.woowacourse.tecobrary.user.ui.vo.UserJwtInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User save(UserGithubInfo userGithubInfo) {
        return userRepository.save(new User(userGithubInfo, new UserAuthorization(Authorization.NONE)));
    }

    public User findByGithubId(String githubId) {
        return userRepository.getUserByUserGithubInfoGithubId(githubId)
                .orElseThrow(NotFoundGithubUserException::new);
    }

    public UserJwtInfoVo findUserJwtInfoByUserNo(String userNo) {
        return UserJwtVoMapper.map(findById(userNo));
    }

    private User findById(String userNo) {
        return userRepository.findById(Long.valueOf(userNo))
                .orElseThrow(NotFoundGithubUserException::new);
    }

    public long countOfUser() {
        return userRepository.count();
    }

    public List<UserInfoDto> findUsersOnPage(final int page, final int number) {
        Page<User> pageUsers = userRepository.findAll(PageRequest.of(page - 1, number));
        return pageUsers.getContent()
                .stream()
                .map(UserInfoDtoMapper::toDto)
                .collect(toList());
    }

    public UserInfoDto findUserById(final long id) {
        User user = userRepository.findById(id)
                .orElseThrow(NotFoundUserException::new);
        return UserInfoDtoMapper.toDto(user);
    }

    @Transactional
    public Long updateUserAuth(final UserAuthDto userAuthDto) {
        User user = userRepository.findById(userAuthDto.getId())
                .orElseThrow(NotFoundUserException::new);
        user.updateAuthorization(userAuthDto.getAuthorization());
        return user.getId();
    }

    @Transactional
    public Long updateUserName(final UserNameDto userNameDto) {
        User user = userRepository.findById(userNameDto.getId())
                .orElseThrow(NotFoundUserException::new);
        user.updateUserName(userNameDto.getNewName());
        return user.getId();
    }

    public boolean existsByUserId(final Long userId) {
        return userRepository.existsById(userId);
    }
}
