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
import com.woowacourse.tecobrary.user.command.util.UserJwtVoMapper;
import com.woowacourse.tecobrary.user.ui.vo.UserJwtInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return userRepository.save(new User(userGithubInfo, new UserAuthorization(Authorization.none)));
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
}
