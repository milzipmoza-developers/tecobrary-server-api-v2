//package librarybook.application;
//
//import com.woowacourse.tecobrary.librarybook.application.ESLibraryBookService;
//import com.woowacourse.tecobrary.librarybook.common.LibraryBookStatic;
//import com.woowacourse.tecobrary.librarybook.domain.ESLibraryBookRepository;
//import com.woowacourse.tecobrary.librarybook.ui.dto.LibraryBookResponseDto;
//import com.woowacourse.tecobrary.librarybook.util.LibraryBookMapper;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//
//import java.util.Collections;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.mock;
//
//class ESLibraryBookServiceTest implements LibraryBookStatic {
//
//    private ESLibraryBookRepository esLibraryBookRepository;
//    private ESLibraryBookService esLibraryBookService;
//
//    @DisplayName("키워드를 포함하는 도서를 조회한다.")
//    @Test
//    void searchLibraryBooks() {
//        String keyword = "웹";
//        int page = 1;
//        int size = 10;
//
//        esLibraryBookRepository = mock(ESLibraryBookRepository.class);
//        esLibraryBookService = new ESLibraryBookService(esLibraryBookRepository);
//        Mockito.when(esLibraryBookRepository.searchByKeyword(any(String.class), any(PageRequest.class)))
//                .thenReturn(new PageImpl<>(
//                        Collections.singletonList(LibraryBookMapper.toResponseDto(TEST_LIBRARY_BOOK_19)),
//                        PageRequest.of(page, size), 1L));
//
//        List<LibraryBookResponseDto> libraryBooks = esLibraryBookService.searchLibraryBook(keyword, page, size);
//
//        assertThat(libraryBooks).hasSize(1);
//    }
//}