package com.woowacourse.tecobrary.serial.common;

import com.woowacourse.tecobrary.librarybook.common.LibraryBookStatic;
import com.woowacourse.tecobrary.serial.domain.Serial;
import com.woowacourse.tecobrary.serial.domain.SerialInfo;
import com.woowacourse.tecobrary.serial.domain.SerialLibraryBook;
import com.woowacourse.tecobrary.serial.domain.SerialRentStatus;

public interface SerialStatic extends LibraryBookStatic {
    Long TEST_SERIAL_NUMBER  = 5L;
    Long TEST_BOOK_ID = TEST_LIBRARY_BOOK_ID;
    boolean TEST_RENT_STATUS = false;

    SerialInfo TEST_SERIAL_INFO = new SerialInfo(TEST_SERIAL_NUMBER);
    SerialLibraryBook TEST_SERIAL_LIBRARY_BOOK = new SerialLibraryBook(TEST_BOOK_ID);
    SerialRentStatus TEST_SERIAL_RENT_STATUS = new SerialRentStatus(TEST_RENT_STATUS);
    Serial TEST_SERIAL_NOT_RENT = new Serial(TEST_SERIAL_INFO, TEST_SERIAL_LIBRARY_BOOK, TEST_SERIAL_RENT_STATUS);

    Long TEST_SERIAL_NUMBER_02  = 2L;
    Long TEST_BOOK_ID_02 = TEST_LIBRARY_BOOK_ID_02;
    boolean TEST_RENT_STATUS_02 = false;

    SerialInfo TEST_SERIAL_INFO_02 = new SerialInfo(TEST_SERIAL_NUMBER_02);
    SerialLibraryBook TEST_SERIAL_LIBRARY_BOOK_02 = new SerialLibraryBook(TEST_BOOK_ID_02);
    SerialRentStatus TEST_SERIAL_RENT_STATUS_02 = new SerialRentStatus(TEST_RENT_STATUS_02);
    Serial TEST_SERIAL_NOT_RENT_02 = new Serial(TEST_SERIAL_INFO_02, TEST_SERIAL_LIBRARY_BOOK_02, TEST_SERIAL_RENT_STATUS_02);

    Long TEST_SERIAL_NUMBER_03  = 3L;
    Long TEST_BOOK_ID_03 = TEST_LIBRARY_BOOK_ID_02;
    boolean TEST_RENT_STATUS_03 = true;

    SerialInfo TEST_SERIAL_INFO_03 = new SerialInfo(TEST_SERIAL_NUMBER_03);
    SerialLibraryBook TEST_SERIAL_LIBRARY_BOOK_03 = new SerialLibraryBook(TEST_BOOK_ID_03);
    SerialRentStatus TEST_SERIAL_RENT_STATUS_03 = new SerialRentStatus(TEST_RENT_STATUS_03);
    Serial TEST_SERIAL_ALREADY_RENT = new Serial(TEST_SERIAL_INFO_03, TEST_SERIAL_LIBRARY_BOOK_03, TEST_SERIAL_RENT_STATUS_03);

    Long TEST_SERIAL_NUMBER_04  = 4L;
    Long TEST_BOOK_ID_04 = TEST_LIBRARY_BOOK_ID_19;
    boolean TEST_RENT_STATUS_04 = true;

    SerialInfo TEST_SERIAL_INFO_04 = new SerialInfo(TEST_SERIAL_NUMBER_04);
    SerialLibraryBook TEST_SERIAL_LIBRARY_BOOK_04 = new SerialLibraryBook(TEST_BOOK_ID_04);
    SerialRentStatus TEST_SERIAL_RENT_STATUS_04 = new SerialRentStatus(TEST_RENT_STATUS_04);
    Serial TEST_SERIAL_ALREADY_RENT_02 = new Serial(TEST_SERIAL_INFO_04, TEST_SERIAL_LIBRARY_BOOK_04, TEST_SERIAL_RENT_STATUS_04);

    Long TEST_SERIAL_NUMBER_05  = 5L;
    Long TEST_BOOK_ID_05 = TEST_LIBRARY_BOOK_ID;
    boolean TEST_RENT_STATUS_05 = false;

    SerialInfo TEST_SERIAL_INFO_05 = new SerialInfo(TEST_SERIAL_NUMBER_05);
    SerialLibraryBook TEST_SERIAL_LIBRARY_BOOK_05 = new SerialLibraryBook(TEST_BOOK_ID_05);
    SerialRentStatus TEST_SERIAL_RENT_STATUS_05 = new SerialRentStatus(TEST_RENT_STATUS_05);
    Serial TEST_SERIAL_NOT_RENT_05 = new Serial(TEST_SERIAL_INFO_05, TEST_SERIAL_LIBRARY_BOOK_05, TEST_SERIAL_RENT_STATUS_05);

    Long TEST_SERIAL_NUMBER_06  = 6L;
    Long TEST_BOOK_ID_06 = TEST_LIBRARY_BOOK_ID_19;
    boolean TEST_RENT_STATUS_06 = true;

    SerialInfo TEST_SERIAL_INFO_06 = new SerialInfo(TEST_SERIAL_NUMBER_06);
    SerialLibraryBook TEST_SERIAL_LIBRARY_BOOK_06 = new SerialLibraryBook(TEST_BOOK_ID_06);
    SerialRentStatus TEST_SERIAL_RENT_STATUS_06 = new SerialRentStatus(TEST_RENT_STATUS_06);
    Serial TEST_SERIAL_ALREADY_RENT_06 = new Serial(TEST_SERIAL_INFO_06, TEST_SERIAL_LIBRARY_BOOK_06, TEST_SERIAL_RENT_STATUS_06);

}
