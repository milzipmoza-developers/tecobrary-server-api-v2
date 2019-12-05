package com.woowacourse.tecobrary.wishbook.command.application;

import com.woowacourse.tecobrary.librarybook.application.LibraryBookService;
import com.woowacourse.tecobrary.librarybook.common.LibraryBookStatic;
import com.woowacourse.tecobrary.librarybook.ui.dto.LibraryBookDto;
import com.woowacourse.tecobrary.librarybook.ui.dto.LibraryBookEnrollDto;
import com.woowacourse.tecobrary.wishbook.common.WishBookStatic;
import com.woowacourse.tecobrary.wishbook.ui.dto.WishBookEnrollResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class WishBookEnrollServiceTest implements WishBookStatic, LibraryBookStatic {

    @Mock
    private WishBookService wishBookService;

    @Mock
    private LibraryBookService libraryBookService;

    @InjectMocks
    private WishBookEnrollService wishBookEnrollService;

    @DisplayName("enrollLibraryBookByWishBookId 가 성공적으로 동작한다.")
    @Test
    void successfullyEnrollLibraryBookByWishBookId() {
        given(wishBookService.findNotEnrolledById(99L)).willReturn(TEST_WISH_BOOK);
        given(libraryBookService.enrollWishBook(any(LibraryBookEnrollDto.class))).willReturn(TEST_LIBRARY_BOOK_ENROLL_DTO_99);
        ReflectionTestUtils.setField(TEST_LIBRARY_BOOK_ENROLL_DTO_99, "id", 99L);

        WishBookEnrollResponseDto enrolledWishBook = wishBookEnrollService.enrollLibraryBookByWishBookId(99L);

        assertThat(enrolledWishBook.getEnrolledDate()).isNotNull();

        LibraryBookDto enrolledLibraryBook = enrolledWishBook.getLibraryBook();

        assertThat(enrolledLibraryBook.getTitle()).isEqualTo(TEST_LIBRARY_BOOK_TITLE_99);
        assertThat(enrolledLibraryBook.getAuthor()).isEqualTo(TEST_LIBRARY_BOOK_AUTHOR_99);
        assertThat(enrolledLibraryBook.getImage()).isEqualTo(TEST_LIBRARY_BOOK_IMAGE_99);
        assertThat(enrolledLibraryBook.getIsbn()).isEqualTo(TEST_LIBRARY_BOOK_ISBN_99);
        assertThat(enrolledLibraryBook.getDescription()).isEqualTo(TEST_LIBRARY_BOOK_DESCRIPTION_99);
    }
}