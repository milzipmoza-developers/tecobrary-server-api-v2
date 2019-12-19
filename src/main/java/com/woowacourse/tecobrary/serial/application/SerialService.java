package com.woowacourse.tecobrary.serial.application;

import com.woowacourse.tecobrary.renthistory.domain.RentSerial;
import com.woowacourse.tecobrary.serial.domain.Serial;
import com.woowacourse.tecobrary.serial.domain.SerialRepository;
import com.woowacourse.tecobrary.serial.exception.NotFoundSerialNumberException;
import com.woowacourse.tecobrary.serial.exception.NotFoundSerialTargetException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public List<Serial> findSerialsByBookId(final Long bookId) {
        return serialRepository.findAllBySerialLibraryBookBookId(bookId);
    }

    @Transactional
    public void deleteBySerialNumber(final Long serialNumber) {
        checkExistSerialNumber(serialNumber);
        serialRepository.deleteBySerialInfoSerialNumber(serialNumber);
    }

    private void checkExistSerialNumber(final Long serialNumber) {
        if (!serialRepository.existsBySerialInfoSerialNumber(serialNumber)) {
            throw new NotFoundSerialNumberException();
        }
    }

    public Serial findByRentSerial(final RentSerial rentSerial) {
        return serialRepository.findById(rentSerial.getSerialId())
                .orElseThrow(NotFoundSerialTargetException::new);
    }

    public Serial findBySerialNumber(final Long serial) {
        return serialRepository.findBySerialInfoSerialNumber(serial)
                .orElseThrow(NotFoundSerialNumberException::new);
    }

    public boolean checkBySerialNumberIsRent(Long id) {
        return serialRepository.existsBySerialInfoSerialNumberAndSerialRentStatusIsTrue(id);
    }
}
