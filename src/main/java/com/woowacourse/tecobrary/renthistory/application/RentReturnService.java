package com.woowacourse.tecobrary.renthistory.application;

import com.woowacourse.tecobrary.librarybook.application.LibraryBookService;
import com.woowacourse.tecobrary.librarybook.domain.LibraryBook;
import com.woowacourse.tecobrary.renthistory.domain.RentHistory;
import com.woowacourse.tecobrary.renthistory.ui.dto.RentHistoryRequest;
import com.woowacourse.tecobrary.renthistory.ui.dto.RentResponseDto;
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

    private static final String RENT_SUCCESS_MESSAGE = "대여에 성공하였습니다.";

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

    public RentResponseDto rent(final RentHistoryRequest rentRequestDto) {
        checkRentConditions(rentRequestDto);
        Serial serial = doRent(rentRequestDto);
        RentHistory rentHistory = rentHistoryService.createRentHistory(rentRequestDto);
        LibraryBook libraryBook = libraryBookService.findByBookId(serial.getBookId());
        return RentHistoryMapper.builder()
                .serial(serial)
                .rentHistory(rentHistory)
                .libraryBook(libraryBook)
                .message(RENT_SUCCESS_MESSAGE)
                .build();
    }

    private void checkRentConditions(final RentHistoryRequest rentRequestDto) {
        checkExistUser(rentRequestDto);
        checkExistSerialNumber(rentRequestDto);
        checkRentStatus(rentRequestDto);
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

    private void checkRentStatus(final RentHistoryRequest rentRequestDto) {
        if (serialService.checkBySerialNumberIsRent(rentRequestDto.getSerial())) {
            throw new AlreadyRentBookException(rentRequestDto);
        }
    }

    @Transactional
    protected Serial doRent(final RentHistoryRequest rentRequestDto) {
        Serial serial = serialService.findBySerialNumber(rentRequestDto.getSerial());
        serial.updateStatusToRent();
        return serial;
    }
}
