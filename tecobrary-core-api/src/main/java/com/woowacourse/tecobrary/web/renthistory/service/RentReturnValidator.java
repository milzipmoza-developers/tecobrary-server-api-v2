package com.woowacourse.tecobrary.web.renthistory.service;

import com.woowacourse.tecobrary.domain.serial.entity.Serial;
import com.woowacourse.tecobrary.domain.serial.repository.SerialRepository;
import com.woowacourse.tecobrary.domain.user.entity.User;
import com.woowacourse.tecobrary.domain.user.repository.UserRepository;
import com.woowacourse.tecobrary.web.renthistory.exception.RentReturnSerialNotFoundException;
import com.woowacourse.tecobrary.web.renthistory.exception.RentReturnUserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RentReturnValidator {

    private final UserRepository userRepository;
    private final SerialRepository serialRepository;

    public User validUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(RentReturnUserNotFoundException::new);
    }

    public Serial validSerial(Long serialNumber) {
        return serialRepository.findBySerialNumber(serialNumber)
                .orElseThrow(RentReturnSerialNotFoundException::new);
    }
}
