package com.woowacourse.tecobrary.wishbook.command.application;

import com.woowacourse.tecobrary.wishbook.command.domain.DuplicatedWishBookIsbnException;
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
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
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

        given(wishBookRepository.findAllByDeletedAtNull(any(PageRequest.class)))
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

        assertThrows(DuplicatedWishBookIsbnException.class,
                () -> wishBookService.createWishBook(WishBookInfoDtoMapper.toDto(TEST_CREATE_WISH_BOOK)));
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

        assertThrows(NotFoundWishBookException.class, () -> wishBookService.findById(1L));
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

    @DisplayName("Soft Delete 되지 않은 WishBook 를 조회한다.")
    @Test
    void successfullyFindBySoftExist() {
        given(wishBookRepository.findByIdAndDeletedAtNull(any(Long.class))).willReturn(Optional.of(TEST_WISH_BOOK_02));

        WishBook existWishBook = wishBookService.findNotEnrolledById(2L);

        assertThat(existWishBook.getImage()).isEqualTo(TEST_COVER_URL_02);
        assertThat(existWishBook.getTitle()).isEqualTo(TEST_TITLE_02);
        assertThat(existWishBook.getAuthor()).isEqualTo(TEST_AUTHOR_02);
        assertThat(existWishBook.getIsbn()).isEqualTo(TEST_ISBN_02);
        assertThat(existWishBook.getDescription()).isEqualTo(TEST_DESCRIPTION_02);
        assertThat(existWishBook.getUserId()).isEqualTo(TEST_USER_ID_02);
        assertThat(existWishBook.getDeletedAt()).isNull();
    }

    @DisplayName("Soft Delete 된 WishBook 에 findByIdAndDeleteAtNull 을 실행하면 조회를 실패한다.")
    @Test
    void failedFindBySoftExistAlreadySoftDeletedWishBook() {
        given(wishBookRepository.findByIdAndDeletedAtNull(any(Long.class))).willThrow(NotFoundWishBookException.class);

        assertThrows(NotFoundWishBookException.class, () -> wishBookService.findNotEnrolledById(1L));
    }

    @DisplayName("Soft Delete 된 WishBook 을 조회한다.")
    @Test
    void successfullyFindBySoftDeleted() {
        given(wishBookRepository.findByIdAndDeletedAtNotNull(any(Long.class))).willReturn(Optional.of(TEST_WISH_BOOK_01));

        WishBook softDeletedWishBook = wishBookService.findEnrolledById(2L);
        ReflectionTestUtils.setField(softDeletedWishBook, "deletedAt", LocalDateTime.now());

        assertThat(softDeletedWishBook.getImage()).isEqualTo(TEST_COVER_URL_01);
        assertThat(softDeletedWishBook.getTitle()).isEqualTo(TEST_TITLE_01);
        assertThat(softDeletedWishBook.getAuthor()).isEqualTo(TEST_AUTHOR_01);
        assertThat(softDeletedWishBook.getIsbn()).isEqualTo(TEST_ISBN_01);
        assertThat(softDeletedWishBook.getDescription()).isEqualTo(TEST_DESCRIPTION_01);
        assertThat(softDeletedWishBook.getUserId()).isEqualTo(TEST_USER_ID_01);
        assertThat(softDeletedWishBook.getDeletedAt()).isNotNull();
    }

    @DisplayName("Soft Delete 된 WishBook 에 findByIdAndDeleteAtNull 을 실행하면 조회를 실패한다.")
    @Test
    void failedFindBySoftDeletedNotSoftDeletedWishBook() {
        given(wishBookRepository.findByIdAndDeletedAtNotNull(any(Long.class))).willThrow(NotFoundWishBookException.class);

        assertThrows(NotFoundWishBookException.class, () -> wishBookService.findNotEnrolledById(1L));
    }

    @DisplayName("WishBook 의 id 로 Soft Delete 에 성공한다.")
    @Test
    void successfullyFindBySoftDeletedNotSoftDeletedWishBook() {
        given(wishBookRepository.existsByIdAndDeletedAtNotNull(1L)).willReturn(true);
        given(wishBookRepository.findById(1L)).willReturn(Optional.of(TEST_WISH_BOOK));

        WishBookInfoDto wishBookInfoDto = wishBookService.softDeleteById(1L);

        assertThat(wishBookInfoDto.getImage()).isEqualTo(TEST_WISH_BOOK_INFO_DTO.getImage());
        assertThat(wishBookInfoDto.getTitle()).isEqualTo(TEST_WISH_BOOK_INFO_DTO.getTitle());
        assertThat(wishBookInfoDto.getAuthor()).isEqualTo(TEST_WISH_BOOK_INFO_DTO.getAuthor());
        assertThat(wishBookInfoDto.getIsbn()).isEqualTo(TEST_WISH_BOOK_INFO_DTO.getIsbn());
        assertThat(wishBookInfoDto.getDescription()).isEqualTo(TEST_WISH_BOOK_INFO_DTO.getDescription());
        assertThat(wishBookInfoDto.getUserId()).isEqualTo(TEST_WISH_BOOK_INFO_DTO.getUserId());
    }

    @DisplayName("존재하지 않거나 이미 처리(soft delete)된 WishBook 은 AlreadySoftDeletedWishBookException 이 발생한다.")
    @Test
    void failedFindBySoftDeletedSoftDeletedWishBook() {
        given(wishBookRepository.existsByIdAndDeletedAtNotNull(1L)).willReturn(false);

        assertThrows(AlreadySoftDeletedWishBookException.class, () -> wishBookService.softDeleteById(1L));
    }
}