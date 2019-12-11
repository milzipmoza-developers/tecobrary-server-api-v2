package com.woowacourse.tecobrary.renthistory.application;

import com.woowacourse.tecobrary.librarybook.application.LibraryBookService;
import com.woowacourse.tecobrary.librarybook.domain.LibraryBook;
import com.woowacourse.tecobrary.renthistory.domain.RentHistory;
import com.woowacourse.tecobrary.renthistory.ui.dto.RentHistoryRequest;
import com.woowacourse.tecobrary.renthistory.ui.dto.RentInfoDto;
import com.woowacourse.tecobrary.renthistory.ui.dto.ReturnInfoDto;
import com.woowacourse.tecobrary.renthistory.ui.dto.ReturnRequestDto;
import com.woowacourse.tecobrary.renthistory.util.RentHistoryMapper;
import com.woowacourse.tecobrary.serial.application.SerialService;
import com.woowacourse.tecobrary.serial.domain.Serial;
import com.woowacourse.tecobrary.serial.exception.NotFoundSerialNumberException;
import com.woowacourse.tecobrary.user.command.application.NotFoundUserException;
import com.woowacourse.tecobrary.user.command.application.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Predicate;

@Service
public class RentReturnService {

    private final UserService userService;
    private final LibraryBookService libraryBookService;
    private final SerialService serialService;
    private final RentHistoryService rentHistoryService;

    @Autowired
    public RentReturnService(final UserService userService,
                             final LibraryBookService libraryBookService,
                             final SerialService serialService,
                             final RentHistoryService rentHistoryService) {
        this.userService = userService;
        this.libraryBookService = libraryBookService;
        this.serialService = serialService;
        this.rentHistoryService = rentHistoryService;
    }

    @Transactional
    public RentInfoDto rent(final RentHistoryRequest rentRequestDto) {
        checkConditions(rentRequestDto);
        checkRentStatus(rentRequestDto);
        Serial serial = doRent(rentRequestDto);
        RentHistory rentHistory = rentHistoryService.createRentHistory(rentRequestDto);
        LibraryBook libraryBook = libraryBookService.findByBookId(serial.getBookId());
        return RentHistoryMapper.toRentInfoDto(libraryBook, rentHistory, serial);
    }

    private void checkConditions(final RentHistoryRequest rentRequestDto) {
        checkExistUser(rentRequestDto);
        checkExistSerialNumber(rentRequestDto);
    }

    private void checkRentStatus(final RentHistoryRequest rentRequestDto) {
        checkRent(rentRequestDto, rentStatusChecker());
    }

    private void checkRent(final RentHistoryRequest rentRequestDto, final Predicate<Long> rentStatusChecker) {
        if (rentStatusChecker.test(rentRequestDto.getSerial())) {
            throw new AlreadyRentBookException(rentRequestDto);
        }
    }

    private Predicate<Long> rentStatusChecker() {
        return serialService::checkBySerialNumberIsRent;
    }

    private void checkExistUser(final RentHistoryRequest rentRequestDto) {
        if (!userService.existsByUserId(rentRequestDto.getUserId())) {
            throw new NotFoundUserException();
        }
    }

    private void checkExistSerialNumber(final RentHistoryRequest rentRequestDto) {
        if (!serialService.existsBySerialNumber(rentRequestDto.getSerial())) {
            throw new NotFoundSerialNumberException();
        }
    }

    private Serial doRent(final RentHistoryRequest rentRequestDto) {
        Serial serial = serialService.findBySerialNumber(rentRequestDto.getSerial());
        serial.updateStatusToRent();
        return serial;
    }

    @Transactional
    public ReturnInfoDto returnBook(final ReturnRequestDto returnRequestDto) {
        checkConditions(returnRequestDto);
        checkReturnStatus(returnRequestDto, returnStatusChecker());
        Serial serial = doReturn(returnRequestDto);
        RentHistory rentHistory = doSoftDelete(returnRequestDto);
        LibraryBook libraryBook = libraryBookService.findByBookId(serial.getBookId());
        return RentHistoryMapper.toReturnInfoDto(libraryBook, serial, rentHistory);
    }

    private void checkReturned(final RentHistoryRequest rentRequestDto, final Predicate<Long> rentStatusChecker) {
        if (rentStatusChecker.test(rentRequestDto.getSerial())) {
            throw new AlreadyReturnBookException(rentRequestDto);
        }
    }

    private Predicate<Long> returnStatusChecker() {
        return id -> !serialService.checkBySerialNumberIsRent(id);
    }

    private void checkReturnStatus(final ReturnRequestDto returnRequestDto, final Predicate<Long> returnStatusChecker) {
        checkReturned(returnRequestDto, returnStatusChecker);
    }

    private Serial doReturn(final ReturnRequestDto returnRequestDto) {
        Serial serial = serialService.findBySerialNumber(returnRequestDto.getSerial());
        serial.updateStatusToReturn();
        return serial;
    }

    private RentHistory doSoftDelete(final ReturnRequestDto returnRequestDto) {
        RentHistory rentHistory = rentHistoryService.findRentHistoryBySerial(returnRequestDto.getSerial());
        if (rentHistory.checkSameUser(returnRequestDto.getUserId())) {
            rentHistory.softDelete();
        }
        return rentHistory;
    }
}
