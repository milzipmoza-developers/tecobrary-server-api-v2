package com.woowacourse.tecobrary.wishbook.command.domain;

import com.woowacourse.tecobrary.wishbook.command.util.WishBookInfoDtoMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class WishBookRepositoryTest {

    @Autowired
    private WishBookRepository wishBookRepository;

    @DisplayName("wish book에 존재하고 library book에 등록되지 않았을 때 true를 반환한다.")
    @Test
    void existsByWishBookInfoIsbnAndDeletedAtNullIsTrue() {
        assertTrue(wishBookRepository.existsByWishBookInfoIsbnAndDeletedAtNull("8965402603 9788965402602"));
    }

    @DisplayName("wish book에 존재하고 library book에 등록되었을때 false를 반환한다.")
    @Test
    void existsByWishBookInfoIsbnAndDeletedAtNullIsFalse() {
        assertFalse(wishBookRepository.existsByWishBookInfoIsbnAndDeletedAtNull("1162241039 9791162241035"));
    }

    @DisplayName("모든 wish book 리스트 페이지에서 등록되지 않은 책 리스트를 반환한다.")
    @Test
    void findAllAndDeletedAtNull() {
        Page<WishBook> pageWishBooks = wishBookRepository.findAllByDeletedAtNull(PageRequest.of(0, 2));

        assertThat(pageWishBooks.getContent()
                .stream()
                .map(WishBookInfoDtoMapper::toDto)
                .collect(toList()).size()).isEqualTo(1);
    }
}