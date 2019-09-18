package com.woowacourse.tecobrary.user.domain;

import com.woowacourse.tecobrary.user.common.UserStatics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest implements UserStatics {

    @Autowired
    private UserRepository userRepository;

    @DisplayName("User 객체를 정상적으로 저장한다.")
    @Test
    void save() {
        User savedUser = userRepository.save(TEST_USER);
        assertNotNull(savedUser.getUserNo());
    }
}