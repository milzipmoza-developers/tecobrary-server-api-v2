package com.woowacourse.tecobrary.serial.domain;

import com.woowacourse.tecobrary.renthistory.application.AlreadyRentStatusException;
import com.woowacourse.tecobrary.serial.common.SerialStatic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SerialTest implements SerialStatic {

    @DisplayName("성공적으로 rent status 를 true 로 변경한다.")
    @Test
    void successfullyUpdateRentStatusToRent() {
        assertThat(TEST_SERIAL_NOT_RENT.updateStatusToRent()).isTrue();
    }

    @DisplayName("이미 대여 중인 책은 AlreadyRentStatusException 이 발생한다..")
    @Test
    void failedUpdateRentStatusToRent() {
        assertThrows(AlreadyRentStatusException.class, TEST_SERIAL_ALREADY_RENT::updateStatusToRent);
    }
}