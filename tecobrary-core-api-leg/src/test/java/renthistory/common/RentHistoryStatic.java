package renthistory.common;

import com.woowacourse.tecobrary.renthistory.entity.RentHistory;
import com.woowacourse.tecobrary.renthistory.entity.RentSerial;
import com.woowacourse.tecobrary.renthistory.entity.RentUser;
import com.woowacourse.tecobrary.renthistory.ui.dto.RentRequestDto;

public interface RentHistoryStatic {
    Long TEST_RENT_HISTORY_ID = 37L;
    Long TEST_RENT_HISTORY_SERIAL_ID = 5L;
    Long TEST_RENT_HISTORY_USER_ID_01 = 1L;
    Long TEST_RENT_HISTORY_USER_ID_16 = 16L;

    RentSerial TEST_RENT_SERIAL = new RentSerial(TEST_RENT_HISTORY_SERIAL_ID);
    RentUser TEST_RENT_USER = new RentUser(TEST_RENT_HISTORY_USER_ID_16);
    RentHistory TEST_RENT_HISTORY = new RentHistory(TEST_RENT_SERIAL, TEST_RENT_USER);

    Long TEST_RENT_HISTORY_ID_02 = 39L;
    Long TEST_RENT_HISTORY_SERIAL_ID_02 = 2L;
    Long TEST_RENT_HISTORY_USER_ID_02 = 16L;

    RentSerial TEST_RENT_SERIAL_02 = new RentSerial(TEST_RENT_HISTORY_SERIAL_ID_02);
    RentUser TEST_RENT_USER_02 = new RentUser(TEST_RENT_HISTORY_USER_ID_02);
    RentHistory TEST_RENT_HISTORY_02 = new RentHistory(TEST_RENT_SERIAL_02, TEST_RENT_USER_02);

    Long TEST_RENT_HISTORY_ID_03 = 20L;
    Long TEST_RENT_HISTORY_SERIAL_ID_03 = 3L;
    Long TEST_RENT_HISTORY_USER_ID_03 = 16L;

    RentSerial TEST_RENT_SERIAL_03 = new RentSerial(TEST_RENT_HISTORY_SERIAL_ID_03);
    RentUser TEST_RENT_USER_03 = new RentUser(TEST_RENT_HISTORY_USER_ID_03);
    RentHistory TEST_RENT_HISTORY_03 = new RentHistory(TEST_RENT_SERIAL_03, TEST_RENT_USER_03);

    RentRequestDto TEST_RENT_REQUEST_DTO = new RentRequestDto(TEST_RENT_HISTORY_SERIAL_ID, TEST_RENT_HISTORY_USER_ID_01);
    RentSerial TEST_RENT_SERIAL_05 = new RentSerial(TEST_RENT_HISTORY_SERIAL_ID);
    RentUser TEST_RENT_USER_01 = new RentUser(TEST_RENT_HISTORY_USER_ID_01);
    RentHistory TEST_RENT_HISTORY_RENT_BOOK = new RentHistory(TEST_RENT_SERIAL_05, TEST_RENT_USER_01);

    int TEST_RENT_HISTORY_USER_ID_33 = 33;
    String TEST_RENT_HISTORY_TITLE_USER_ID_33 = "객체 지향과 디자인 패턴 (개발자가 반드시 정복해야 할)";
    int TEST_RENT_HISTORY_SERIAL_USER_ID_33 = 10;
    int TEST_RENT_HISTORY_ID_USER_ID_33 = 45;
}
