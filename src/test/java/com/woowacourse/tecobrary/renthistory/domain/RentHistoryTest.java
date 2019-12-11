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

package com.woowacourse.tecobrary.renthistory.domain;

import com.woowacourse.tecobrary.renthistory.common.RentHistoryStatic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RentHistoryTest implements RentHistoryStatic {

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