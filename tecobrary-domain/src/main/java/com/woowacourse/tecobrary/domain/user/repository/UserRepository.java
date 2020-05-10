package com.woowacourse.tecobrary.domain.user.repository;

import com.woowacourse.tecobrary.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
