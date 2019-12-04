package com.woowacourse.tecobrary.renthistory.common;

import com.woowacourse.tecobrary.renthistory.domain.RentHistory;
import com.woowacourse.tecobrary.renthistory.domain.RentSerial;
import com.woowacourse.tecobrary.renthistory.domain.RentUser;

public interface RentHistoryStatic {
    Long TEST_RENT_HISTORY_ID = 37L;
    Long TEST_RENT_HISTORY_SERIAL_ID = 5L;
    Long TEST_RENT_HISTORY_USER_ID = 16L;

    RentSerial TEST_RENT_SERIAL = new RentSerial(TEST_RENT_HISTORY_SERIAL_ID);
    RentUser TEST_RENT_USER = new RentUser(TEST_RENT_HISTORY_USER_ID);
    RentHistory TEST_RENT_HISTORY = new RentHistory(TEST_RENT_SERIAL, TEST_RENT_USER);

    Long TEST_RENT_HISTORY_ID_02 = 39L;
    Long TEST_RENT_HISTORY_SERIAL_ID_02 = 2L;
    Long TEST_RENT_HISTORY_USER_ID_02 = 16L;

    RentSerial TEST_RENT_SERIAL_02 = new RentSerial(TEST_RENT_HISTORY_SERIAL_ID_02);
    RentUser TEST_RENT_USER_02 = new RentUser(TEST_RENT_HISTORY_USER_ID_02);
    RentHistory TEST_RENT_HISTORY_02 = new RentHistory(TEST_RENT_SERIAL_02, TEST_RENT_USER_02);
}