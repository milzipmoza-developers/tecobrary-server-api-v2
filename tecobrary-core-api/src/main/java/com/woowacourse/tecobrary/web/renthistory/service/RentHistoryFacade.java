package com.woowacourse.tecobrary.web.renthistory.service;

import com.woowacourse.tecobrary.renthistory.entity.RentHistory;
import com.woowacourse.tecobrary.renthistory.repository.RentHistoryRepository;
import com.woowacourse.tecobrary.serial.entity.Serial;
import com.woowacourse.tecobrary.user.entity.User;
import com.woowacourse.tecobrary.web.renthistory.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RentHistoryFacade {

    private final RentHistoryRepository rentHistoryRepository;
    private final RentHistoryConverter rentHistoryConverter;
    private final RentReturnValidator rentReturnValidator;

    public List<RentHistoryInfoDto> findAllByUserId(Long userId) {
        List<RentHistory> histories = rentHistoryRepository.findAllByUserIdAndDeletedAtIsNotNull(userId);
        return histories.stream()
                .map(rentHistoryConverter::convertInfoDto)
                .collect(Collectors.toList());
    }

    public List<ReturnHistoryInfoDto> findAllReturnedByUserId(Long userId) {
        List<RentHistory> histories = rentHistoryRepository.findAllByUserIdAndDeletedAtIsNull(userId);
        return histories.stream()
                .map(rentHistoryConverter::convertReturnInfoDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public RentResponse rentBook(RentRequest requestDto) {
        User user = rentReturnValidator.validUser(requestDto.getUserId());
        Serial serial = rentReturnValidator.validSerial(requestDto.getSerial());

        serial.doRent();

        RentHistory rentHistory = rentHistoryRepository.save(RentHistory.builder()
                .user(user)
                .serial(serial)
                .build());

        return rentHistoryConverter.convertRentResponse(rentHistory);
    }

    @Transactional
    public ReturnResponse returnBook(ReturnRequest requestDto) {
        User user = rentReturnValidator.validUser(requestDto.getUserId());
        Serial serial = rentReturnValidator.validSerial(requestDto.getSerial());

        RentHistory rentHistory = rentHistoryRepository.findBySerialSerialNumberAndUserIdAndDeletedAtIsNull(serial.getSerialNumber(), user.getId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 대여 내역입니다."));

        serial.doReturn();

        rentHistory.softDelete();

        return rentHistoryConverter.convertReturnResponse(rentHistory);
    }
}
