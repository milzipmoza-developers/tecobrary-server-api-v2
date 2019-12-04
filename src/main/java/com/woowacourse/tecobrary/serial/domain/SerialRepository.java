package com.woowacourse.tecobrary.serial.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerialRepository extends JpaRepository<Serial, Long> {

    boolean existsBySerialInfoSerialNumber(Long serialNumber);
}
