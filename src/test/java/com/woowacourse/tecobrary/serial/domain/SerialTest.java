package com.woowacourse.tecobrary.serial.domain;

import com.woowacourse.tecobrary.serial.common.SerialStatic;
import com.woowacourse.tecobrary.serial.exception.AlreadyRentStatusException;
import com.woowacourse.tecobrary.serial.exception.AlreadyReturnStatusException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SerialTest implements SerialStatic {

    @DisplayName("성공적으로 rent status 를 true 로 변경한다.")
    @Test
    void successfullyUpdateRentStatusToRent() {
        assertThat(TEST_SERIAL_NOT_RENT_05.updateStatusToRent()).isTrue();
    }

    @DisplayName("이미 대여 중인 책은 AlreadyRentStatusException 이 발생한다.")
    @Test
    void failedUpdateRentStatusToRent() {
        assertThrows(AlreadyRentStatusException.class, TEST_SERIAL_ALREADY_RENT_06::updateStatusToRent);
    }

    @DisplayName("성공적으로 rent status 를 false 로 변경한다.")
    @Test
    void successfullyUpdateRentStatusToReturned() {
        assertThat(TEST_SERIAL_ALREADY_RENT_02.updateStatusToReturn()).isFalse();
    }

    @DisplayName("이미 대여 중이 아닌 책은 AlreadyReturnStatusException 이 발생한다ㅣ")
    @Test
    void failedUpdateRentStatusToReturned() {
        assertThrows(AlreadyReturnStatusException.class, TEST_SERIAL_NOT_RENT_02::updateStatusToReturn);
    }
}