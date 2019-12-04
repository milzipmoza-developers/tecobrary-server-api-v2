package com.woowacourse.tecobrary.wishbook.command.application;

import com.woowacourse.tecobrary.wishbook.command.domain.ExistWishBookIsbnException;
import com.woowacourse.tecobrary.wishbook.command.domain.NotFoundWishBookException;
import com.woowacourse.tecobrary.wishbook.command.domain.WishBook;
import com.woowacourse.tecobrary.wishbook.command.domain.WishBookRepository;
import com.woowacourse.tecobrary.wishbook.command.util.WishBookInfoDtoMapper;
import com.woowacourse.tecobrary.wishbook.common.WishBookStatic;
import com.woowacourse.tecobrary.wishbook.ui.dto.WishBookInfoDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class WishBookServiceTest implements WishBookStatic {

    @Mock
    private WishBookRepository wishBookRepository;

    @InjectMocks
    private WishBookService wishBookService;

    @DisplayName("특정 페이지의 wishBook list를 조회한다.")
    @Test
    void successfullyFindWishBooksOnPage() {
        List<WishBook> mockWishBooks = Arrays.asList(TEST_WISH_BOOK, TEST_WISH_BOOK_01);

        given(wishBookRepository.findAll(any(PageRequest.class)))
                .willReturn(new PageImpl<>(mockWishBooks, PageRequest.of(1, 2), 2));

        List<WishBookInfoDto> wishBooks = wishBookService.findWishBooksOnPage(1, 2);

        assertThat(wishBooks).hasSize(2);
    }

    @DisplayName("wishBook에 책 정보를 등록한다.")
    @Test
    void successfullySaveWishBook() {
        given(wishBookRepository.existsByWishBookInfoIsbn(TEST_CREATE_ISBN)).willReturn(false);
        given(wishBookRepository.save(TEST_CREATE_WISH_BOOK)).willReturn(TEST_CREATE_WISH_BOOK);

        wishBookService.createWishBook(WishBookInfoDtoMapper.toDto(TEST_CREATE_WISH_BOOK));

        verify(wishBookRepository).save(TEST_CREATE_WISH_BOOK);
    }

    @DisplayName("해당하는 Isbn이 존재하면 등록에 실패한다.")
    @Test
    void failedSaveWishBook() {
        given(wishBookRepository.existsByWishBookInfoIsbn(TEST_CREATE_ISBN)).willReturn(true);
        given(wishBookRepository.save(TEST_CREATE_WISH_BOOK)).willReturn(TEST_CREATE_WISH_BOOK);

        assertThrows(ExistWishBookIsbnException.class
                , () -> wishBookService.createWishBook(WishBookInfoDtoMapper.toDto(TEST_CREATE_WISH_BOOK)));
    }

    @DisplayName("해당하는 id 로 WishBook 을 조회한다.")
    @Test
    void successfullyFindWishBook() {
        given(wishBookRepository.findById(1L)).willReturn(Optional.of(TEST_WISH_BOOK));

        WishBookInfoDto wishBookInfoDto = wishBookService.findById(1L);

        assertThat(wishBookInfoDto.getIsbn()).isEqualTo(TEST_ISBN);
        assertThat(wishBookInfoDto.getUserId()).isEqualTo(TEST_USER_ID);
    }

    @DisplayName("해당하는 id가 없을 때 조회에 실패한다.")
    @Test
    void failedFindWishBook() {
        given(wishBookRepository.findById(1L)).willReturn(Optional.empty());

        assertThrows(NotFoundWishBookException.class
                , () -> wishBookService.findById(1L));
    }

    @DisplayName("해당하는 id가 존재하면 삭제한다.")
    @Test
    void successfullyDeleteWishBook() {
        given(wishBookRepository.existsById(1L)).willReturn(true);
        willDoNothing().given(wishBookRepository).deleteById(1L);

        wishBookService.deleteWishBook(1L);

        verify(wishBookRepository).deleteById(1L);
    }

    @DisplayName("삭제하려는 wishBook의 id가 존재하지 않을 때 삭제에 실패한다.")
    @Test
    void failedDeleteWishBook() {
        given(wishBookRepository.existsById(1L)).willReturn(false);

        assertThrows(NotFoundWishBookException.class, () -> wishBookService.deleteWishBook(1L));
    }

    @DisplayName("id가 존재한다.")
    @Test
    void successExistsById() {
        given(wishBookRepository.existsById(1L)).willReturn(true);

        assertThat(wishBookService.existsById(1L)).isEqualTo(true);
    }
}