/*
 * @(#) RentHistoryTest.java
 *
 * v 1.0.0
 *
 * 2019.12.11
 *
 * Copyright (c) 2019 woowacourse, mizipmoza-developers, thedevluffy
 * All rights reserved
 */

package com.woowacourse.tecobrary.renthistory.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RentHistoryTest {
    private static RentSerial TEST_RENT_SERIAL_05 = new RentSerial(5L);
    private static RentUser TEST_RENT_USER_01 = new RentUser(1L);
    private static RentHistory TEST_RENT_HISTORY_RENT_BOOK = new RentHistory(TEST_RENT_SERIAL_05, TEST_RENT_USER_01);

    @DisplayName("성공적으로 soft delete 를 수행한다.")
    @Test
    void successfullySoftDelete() {
        assertThat(TEST_RENT_HISTORY_RENT_BOOK.softDelete()).isNotNull();
    }

    @DisplayName("동일하지 않은 유저인지 확인한다.")
    @Test
    void checkSameUserIsTrue() {
        assertThat(TEST_RENT_HISTORY_RENT_BOOK.isDifferentUser(1L)).isEqualTo(false);
    }
}