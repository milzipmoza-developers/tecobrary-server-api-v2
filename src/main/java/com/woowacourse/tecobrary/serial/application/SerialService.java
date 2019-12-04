package com.woowacourse.tecobrary.serial.application;

import com.woowacourse.tecobrary.renthistory.domain.RentSerial;
import com.woowacourse.tecobrary.serial.domain.Serial;
import com.woowacourse.tecobrary.serial.domain.SerialRepository;
import com.woowacourse.tecobrary.serial.exception.NotFoundSerialTargetException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SerialService {

    private final SerialRepository serialRepository;

    @Autowired
    public SerialService(final SerialRepository serialRepository) {
        this.serialRepository = serialRepository;
    }

    public Serial save(final Serial serial) {
        return serialRepository.save(serial);
    }

    public boolean existsBySerialNumber(final Long serialNumber) {
        return serialRepository.existsBySerialInfoSerialNumber(serialNumber);
    }

    public Serial findByRentSerial(final RentSerial rentSerial) {
        return serialRepository.findById(rentSerial.getSerialId())
                .orElseThrow(NotFoundSerialTargetException::new);
    }
}
