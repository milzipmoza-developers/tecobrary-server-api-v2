package com.woowacourse.tecobrary.librarybook.repository;

import com.woowacourse.tecobrary.librarybook.entity.LibraryBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryBookRepository extends JpaRepository<LibraryBook, Long> {
}
