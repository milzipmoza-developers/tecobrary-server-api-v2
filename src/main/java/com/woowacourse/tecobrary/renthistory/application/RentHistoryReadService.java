package com.woowacourse.tecobrary.renthistory.application;

import com.woowacourse.tecobrary.librarybook.application.LibraryBookService;
import com.woowacourse.tecobrary.librarybook.domain.LibraryBook;
import com.woowacourse.tecobrary.renthistory.domain.RentHistory;
import com.woowacourse.tecobrary.renthistory.ui.dto.RentHistoryDto;
import com.woowacourse.tecobrary.renthistory.util.RentHistoryMapper;
import com.woowacourse.tecobrary.serial.application.SerialService;
import com.woowacourse.tecobrary.serial.domain.Serial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RentHistoryReadService {

    private final RentHistoryService rentHistoryService;
    private final LibraryBookService libraryBookService;
    private final SerialService serialService;

    @Autowired
    public RentHistoryReadService(final RentHistoryService rentHistoryService,
                                  final LibraryBookService libraryBookService,
                                  final SerialService serialService) {
        this.rentHistoryService = rentHistoryService;
        this.libraryBookService = libraryBookService;
        this.serialService = serialService;
    }

    public List<RentHistoryDto> findAllByUserId(final Long userId) {
        List<RentHistory> rentHistories = rentHistoryService.findAllByRentUser(userId);
        return convertDtos(rentHistories);
    }

    private List<RentHistoryDto> convertDtos(final List<RentHistory> rentHistories) {
        List<RentHistoryDto> rentHistoryDtos = new ArrayList<>();
        for (RentHistory rentHistory : rentHistories) {
            Serial serial = serialService.findByRentSerial(rentHistory.getRentSerial());
            LibraryBook libraryBook = libraryBookService.findByBookId(serial.getBookId());
            rentHistoryDtos.add(RentHistoryMapper.toDto(rentHistory, libraryBook));
        }
        return rentHistoryDtos;
    }
}
