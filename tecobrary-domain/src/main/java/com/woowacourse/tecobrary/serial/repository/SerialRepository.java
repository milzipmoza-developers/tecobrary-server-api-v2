package com.woowacourse.tecobrary.serial.repository;

import com.woowacourse.tecobrary.serial.entity.Serial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerialRepository extends JpaRepository<Serial, Long> {
}
