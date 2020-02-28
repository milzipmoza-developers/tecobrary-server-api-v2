package com.woowacourse.tecobrary.serial.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SerialTest {

    private static SerialInfo TEST_SERIAL_INFO_05 = new SerialInfo(5L);
    private static SerialLibraryBook TEST_SERIAL_LIBRARY_BOOK_05 = new SerialLibraryBook(1L);
    private static SerialRentStatus TEST_SERIAL_RENT_STATUS_05 = new SerialRentStatus(false);
    private static Serial TEST_SERIAL_NOT_RENT_05 = new Serial(TEST_SERIAL_INFO_05, TEST_SERIAL_LIBRARY_BOOK_05, TEST_SERIAL_RENT_STATUS_05);

    private static SerialInfo TEST_SERIAL_INFO_06 = new SerialInfo(6L);
    private static SerialLibraryBook TEST_SERIAL_LIBRARY_BOOK_06 = new SerialLibraryBook(19L);
    private static SerialRentStatus TEST_SERIAL_RENT_STATUS_06 = new SerialRentStatus(true);
    private static Serial TEST_SERIAL_ALREADY_RENT_06 = new Serial(TEST_SERIAL_INFO_06, TEST_SERIAL_LIBRARY_BOOK_06, TEST_SERIAL_RENT_STATUS_06);

    private static SerialInfo TEST_SERIAL_INFO_04 = new SerialInfo(4L);
    private static SerialLibraryBook TEST_SERIAL_LIBRARY_BOOK_04 = new SerialLibraryBook(19L);
    private static SerialRentStatus TEST_SERIAL_RENT_STATUS_04 = new SerialRentStatus(true);
    private static Serial TEST_SERIAL_ALREADY_RENT_02 = new Serial(TEST_SERIAL_INFO_04, TEST_SERIAL_LIBRARY_BOOK_04, TEST_SERIAL_RENT_STATUS_04);

    private static SerialInfo TEST_SERIAL_INFO_02 = new SerialInfo(2L);
    private static SerialLibraryBook TEST_SERIAL_LIBRARY_BOOK_02 = new SerialLibraryBook(2L);
    private static SerialRentStatus TEST_SERIAL_RENT_STATUS_02 = new SerialRentStatus(false);
    private static Serial TEST_SERIAL_NOT_RENT_02 = new Serial(TEST_SERIAL_INFO_02, TEST_SERIAL_LIBRARY_BOOK_02, TEST_SERIAL_RENT_STATUS_02);

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