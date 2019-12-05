package com.woowacourse.tecobrary.wishbook.command.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishBookRepository extends JpaRepository<WishBook, Long> {

    Optional<WishBook> findByIdAndDeletedAtNull(Long id);

    Optional<WishBook> findByIdAndDeletedAtNotNull(Long id);

    boolean existsByIdAndDeletedAtNotNull(Long id);

    boolean existsByWishBookInfoIsbn(String isbn);

    Page<WishBook> findAllByDeletedAtNull(Pageable pageable);
}
