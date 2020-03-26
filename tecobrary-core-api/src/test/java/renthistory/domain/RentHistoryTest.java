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

package renthistory.domain;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import renthistory.common.RentHistoryStatic;

import static org.assertj.core.api.Assertions.assertThat;

class RentHistoryTest implements RentHistoryStatic {

    // TODO : 테스트가 깨지는 문제 해결
    @DisplayName("성공적으로 soft delete 를 수행한다.")
    @Test
    @Disabled
    void successfullySoftDelete() {
        assertThat(TEST_RENT_HISTORY_RENT_BOOK.softDelete()).isNotNull();

    }

    @DisplayName("동일하지 않은 유저인지 확인한다.")
    @Test
    void checkSameUserIsTrue() {
        assertThat(TEST_RENT_HISTORY_RENT_BOOK.isDifferentUser(1L)).isEqualTo(false);
    }
}