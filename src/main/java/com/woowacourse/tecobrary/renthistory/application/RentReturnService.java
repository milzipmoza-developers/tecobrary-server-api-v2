package com.woowacourse.tecobrary.renthistory.application;

import com.woowacourse.tecobrary.librarybook.application.LibraryBookService;
import com.woowacourse.tecobrary.librarybook.domain.LibraryBook;
import com.woowacourse.tecobrary.renthistory.domain.NotPermittedUserException;
import com.woowacourse.tecobrary.renthistory.domain.RentHistory;
import com.woowacourse.tecobrary.renthistory.ui.dto.RentHistoryRequest;
import com.woowacourse.tecobrary.renthistory.ui.dto.RentResponseDto;
import com.woowacourse.tecobrary.renthistory.ui.dto.ReturnRequestDto;
import com.woowacourse.tecobrary.renthistory.ui.dto.ReturnResponseDto;
import com.woowacourse.tecobrary.renthistory.util.RentHistoryMapper;
import com.woowacourse.tecobrary.serial.application.SerialService;
import com.woowacourse.tecobrary.serial.domain.Serial;
import com.woowacourse.tecobrary.serial.exception.NotFoundSerialNumberException;
import com.woowacourse.tecobrary.user.command.application.NotFoundUserException;
import com.woowacourse.tecobrary.user.command.application.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RentReturnService {

    public static final String RENT_SUCCESS_MESSAGE = "대여에 성공하였습니다.";
    public static final String RETURN_SUCCESS_MESSAGE = "반납에 성공하였습니다.";

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
    public RentResponseDto rentBook(final RentHistoryRequest rentRequestDto) {
        checkExistence(rentRequestDto);
        checkRentStatus(rentRequestDto);
        Serial serial = doRent(rentRequestDto);
        RentHistory rentHistory = rentHistoryService.createRentHistory(rentRequestDto);
        LibraryBook libraryBook = libraryBookService.findByBookId(serial.getBookId());
        return RentHistoryMapper.rentDtoBuilder()
                .libraryBook(libraryBook)
                .rentHistory(rentHistory)
                .serial(serial)
                .message(RENT_SUCCESS_MESSAGE).build();
    }

    private void checkExistence(final RentHistoryRequest rentRequestDto) {
        checkExistentUser(rentRequestDto);
        checkExistentSerialNumber(rentRequestDto);
    }

    private void checkExistentUser(final RentHistoryRequest rentRequestDto) {
        if (!userService.existsByUserId(rentRequestDto.getUserId())) {
            throw new NotFoundUserException();
        }
    }

    private void checkExistentSerialNumber(final RentHistoryRequest rentRequestDto) {
        if (!serialService.existsBySerialNumber(rentRequestDto.getSerial())) {
            throw new NotFoundSerialNumberException();
        }
    }

    private void checkRentStatus(final RentHistoryRequest rentRequestDto) {
        if (serialService.checkBySerialNumberIsRent(rentRequestDto.getSerial())) {
            throw new AlreadyRentBookException(rentRequestDto);
        }
    }

    private Serial doRent(final RentHistoryRequest rentRequestDto) {
        Serial serial = serialService.findBySerialNumber(rentRequestDto.getSerial());
        serial.updateStatusToRent();
        return serial;
    }

    @Transactional
    public ReturnResponseDto returnBook(final ReturnRequestDto returnRequestDto) {
        checkExistence(returnRequestDto);
        checkReturnedStatus(returnRequestDto);
        Serial serial = doReturn(returnRequestDto);
        RentHistory rentHistory = doSoftDelete(returnRequestDto);
        LibraryBook libraryBook = libraryBookService.findByBookId(serial.getBookId());
        return RentHistoryMapper.returnDtoBuilder()
                .libraryBook(libraryBook)
                .serial(serial)
                .rentHistory(rentHistory)
                .message(RETURN_SUCCESS_MESSAGE).build();
    }

    private void checkReturnedStatus(final RentHistoryRequest rentRequestDto) {
        if (!serialService.checkBySerialNumberIsRent(rentRequestDto.getSerial())) {
            throw new AlreadyReturnBookException(rentRequestDto);
        }
    }

    private Serial doReturn(final ReturnRequestDto returnRequestDto) {
        Serial serial = serialService.findBySerialNumber(returnRequestDto.getSerial());
        serial.updateStatusToReturn();
        return serial;
    }

    private RentHistory doSoftDelete(final ReturnRequestDto returnRequestDto) {
        RentHistory rentHistory = rentHistoryService.findRentHistoryBySerial(returnRequestDto.getSerial());
        checkSameUser(returnRequestDto, rentHistory);
        rentHistory.softDelete();
        return rentHistory;
    }

    private void checkSameUser(final ReturnRequestDto returnRequestDto, final RentHistory rentHistory) {
        if (rentHistory.isDifferentUser(returnRequestDto.getUserId())) {
            throw new NotPermittedUserException();
        }
    }
}
