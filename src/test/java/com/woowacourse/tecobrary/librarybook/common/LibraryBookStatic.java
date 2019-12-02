package com.woowacourse.tecobrary.librarybook.common;

import com.woowacourse.tecobrary.common.model.BookCoverUrl;
import com.woowacourse.tecobrary.common.model.BookInfo;
import com.woowacourse.tecobrary.librarybook.domain.LibraryBook;

public interface LibraryBookStatic {

    String TEST_IMAGE = "https://image.url";
    String TEST_TITLE = "제목";
    String TEST_AUTHOR = "작가";
    String TEST_PUBLISHER = "출판사";
    String TEST_ISBN = "19930705";
    String TEST_DESCRIPTION = "내용 없음";

    Long SAVED_ID = 1L;
    String SAVED_IMAGE = "https://image.book";
    String SAVED_TITLE = "제목";
    String SAVED_AUTHOR = "작가";
    String SAVED_PUBLISHER = "출판사";
    String SAVED_ISBN = "0123";
    String SAVED_DESCRIPTION = "요약";

    LibraryBook TEST_LIBRARY_BOOK = new LibraryBook(new BookCoverUrl(TEST_IMAGE),
            new BookInfo(TEST_TITLE, TEST_AUTHOR, TEST_PUBLISHER, TEST_ISBN, TEST_DESCRIPTION));
}
