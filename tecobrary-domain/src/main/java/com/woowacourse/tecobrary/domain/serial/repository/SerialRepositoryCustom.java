package com.woowacourse.tecobrary.domain.serial.repository;

import com.woowacourse.tecobrary.domain.serial.entity.Serial;

import java.util.List;
import java.util.Optional;

public interface SerialRepositoryCustom {

    List<Serial> findAllSerialByLibraryBookId(Long id);

    Optional<Serial> findBySerialNumberWithLibraryBook(Long serialNumber);
}
