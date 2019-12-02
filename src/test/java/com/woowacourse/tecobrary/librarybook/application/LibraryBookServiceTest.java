package com.woowacourse.tecobrary.librarybook.application;

import com.woowacourse.tecobrary.common.model.BookCoverUrl;
import com.woowacourse.tecobrary.common.model.BookInfo;
import com.woowacourse.tecobrary.librarybook.common.LibraryBookStatic;
import com.woowacourse.tecobrary.librarybook.domain.LibraryBook;
import com.woowacourse.tecobrary.librarybook.domain.LibraryBookRepository;
import com.woowacourse.tecobrary.librarybook.exception.DuplicatedLibraryBookException;
import com.woowacourse.tecobrary.librarybook.ui.dto.LibraryBookCreateResponseDto;
import com.woowacourse.tecobrary.librarybook.ui.dto.LibraryBookRequestDto;
import com.woowacourse.tecobrary.librarybook.ui.dto.LibraryBookResponseDto;
import com.woowacourse.tecobrary.librarybook.ui.dto.LibraryBookTotalCountResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class LibraryBookServiceTest implements LibraryBookStatic {

    private static final long TOTAL_COUNT = 1_000_000L;

    @Mock
    private LibraryBookRepository libraryBookRepository;

    @InjectMocks
    private LibraryBookService libraryBookService;

    private LibraryBookRequestDto libraryBookRequestDto;
    private LibraryBook libraryBook;

    @BeforeEach
    void setUp() {
        libraryBookRequestDto = LibraryBookRequestDto.builder()
                .image(TEST_IMAGE)
                .title(TEST_TITLE)
                .author(TEST_AUTHOR)
                .publisher(TEST_PUBLISHER)
                .isbn(TEST_ISBN)
                .description(TEST_DESCRIPTION)
                .build();

        libraryBook = new LibraryBook(
                new BookCoverUrl(TEST_IMAGE),
                new BookInfo(TEST_TITLE, TEST_AUTHOR, TEST_PUBLISHER, TEST_ISBN, TEST_DESCRIPTION)
        );

        ReflectionTestUtils.setField(libraryBook, "id", 1L);
    }

    @DisplayName("save 가 정상적으로 동작한다.")
    @Test
    void saveSuccess() {
        given(libraryBookRepository.save(any(LibraryBook.class))).willReturn(libraryBook);
        given(libraryBookRepository.existsByLibraryBookInfoIsbn(any(String.class))).willReturn(false);

        LibraryBookCreateResponseDto libraryBookCreateResponseDto = libraryBookService.save(libraryBookRequestDto);

        assertThat(libraryBookCreateResponseDto.getId()).isEqualTo(libraryBook.getId());
        assertThat(libraryBookCreateResponseDto.getMessage()).isEqualTo(libraryBook.getTitle() + " register succeed");
    }

    @DisplayName("중복된 isbn 에 대한 save 가 실패한다.")
    @Test
    void saveFailed() {
        given(libraryBookRepository.save(any(LibraryBook.class))).willReturn(libraryBook);
        given(libraryBookRepository.existsByLibraryBookInfoIsbn(any(String.class))).willReturn(true);

        assertThrows(DuplicatedLibraryBookException.class, () -> libraryBookService.save(libraryBookRequestDto));
    }

    @DisplayName("총 도서 수를 조회한다.")
    @Test
    void readTotalCount() {
        given(libraryBookRepository.count()).willReturn(TOTAL_COUNT);

        LibraryBookTotalCountResponseDto libraryBookTotalCountResponseDto = libraryBookService.count();

        assertThat(libraryBookTotalCountResponseDto.getTotal()).isEqualTo(TOTAL_COUNT);
    }

    @DisplayName("id에 해당하는 도서를 조회한다.")
    @Test
    void readLibraryBook() {
        given(libraryBookRepository.findById(1L)).willReturn(Optional.of(libraryBook));

        LibraryBookResponseDto libraryBookResponseDto = libraryBookService.findById(1L);

        assertThat(libraryBookResponseDto.getId()).isEqualTo(1L);
        assertThat(libraryBookResponseDto.getTitle()).isEqualTo(TEST_TITLE);
        assertThat(libraryBookResponseDto.getAuthor()).isEqualTo(TEST_AUTHOR);
        assertThat(libraryBookResponseDto.getImage()).isEqualTo(TEST_IMAGE);
        assertThat(libraryBookResponseDto.getPublisher()).isEqualTo(TEST_PUBLISHER);
        assertThat(libraryBookResponseDto.getIsbn()).isEqualTo(TEST_ISBN);
        assertThat(libraryBookResponseDto.getDescription()).isEqualTo(TEST_DESCRIPTION);
    }

    @DisplayName("페이지에 해당하는 도서들을 조회한다.")
    @Test
    void readLibraryBooks() {
        List<LibraryBook> mockLibraryBooks = Arrays.asList(TEST_LIBRARY_BOOK, TEST_LIBRARY_BOOK);
        given(libraryBookRepository.findAll(any(PageRequest.class)))
                .willReturn(new PageImpl<>(mockLibraryBooks, PageRequest.of(1, 2), 2));
        List<LibraryBookResponseDto> libraryBooks = libraryBookService.findAll(1, 2);
        assertThat(libraryBooks).hasSize(2);
    }

    @DisplayName("제목에 맞는 도서 정보들을 조회한다.")
    @Test
    void readLibraryBooksByTitle() {
        List<LibraryBook> mockLibraryBooks = Arrays.asList(TEST_LIBRARY_BOOK, TEST_LIBRARY_BOOK);
        given(libraryBookRepository.findAllByLibraryBookInfoTitleContaining(any(String.class), any(PageRequest.class)))
                .willReturn(new PageImpl<>(mockLibraryBooks, PageRequest.of(1, 2), 2));
        List<LibraryBookResponseDto> libraryBooks = libraryBookService.findAllByTitleContaining(TEST_TITLE, 1, 2);
        assertThat(libraryBooks).hasSize(2);
    }
}