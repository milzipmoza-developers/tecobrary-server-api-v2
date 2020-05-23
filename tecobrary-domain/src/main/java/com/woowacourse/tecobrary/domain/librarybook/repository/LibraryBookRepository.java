package com.woowacourse.tecobrary.domain.librarybook.repository;

import com.woowacourse.tecobrary.domain.librarybook.entity.LibraryBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryBookRepository extends JpaRepository<LibraryBook, Long> {
    boolean existsByIsbn(String isbn);
}
