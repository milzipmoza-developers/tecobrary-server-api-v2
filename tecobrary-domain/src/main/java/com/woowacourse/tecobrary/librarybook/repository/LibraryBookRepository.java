package com.woowacourse.tecobrary.librarybook.repository;

import com.woowacourse.tecobrary.librarybook.entity.LibraryBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryBookRepository extends JpaRepository<LibraryBook, Long> {

    boolean existsByLibraryBookInfoIsbn(String isbn);

    Page<LibraryBook> findAllByLibraryBookInfoTitleContaining(String title, Pageable pageable);
}
