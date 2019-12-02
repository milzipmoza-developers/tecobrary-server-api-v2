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

    Long SAVED_ID_AT_ID_01 = 1L;
    String SAVED_IMAGE_AT_ID_01 = "https://bookthumb-phinf.pstatic.net/cover/091/459/09145968.jpg?type=m1&udate=20171011";
    String SAVED_TITLE_AT_ID_01 = "객체지향의 사실과 오해 (역할, 책임, 협력 관점에서 본 객체지향)";
    String SAVED_AUTHOR_AT_ID_01 = "조영호";
    String SAVED_PUBLISHER_AT_ID_01 = "위키북스";
    String SAVED_ISBN_AT_ID_01 = "8998139766 9788998139766";
    String SAVED_DESCRIPTION_AT_ID_01 = "객체지향에 대한 선입견을 버려라!『객체지향의 사실과 오해』는 객체지향이란 무엇인가라는 원론적면서도 다소 위험한 질문에 답하기 위해 쓰여진 책이다. 안타깝게도 많은 사람들이 객체지향의 본질을 오해하고 있다. 가장 널리 퍼져있는 오해는 클래스가 객체지향 프로그래밍의 중심이라는 것이다.... ";

    LibraryBook TEST_LIBRARY_BOOK = new LibraryBook(new BookCoverUrl(TEST_IMAGE),
            new BookInfo(TEST_TITLE, TEST_AUTHOR, TEST_PUBLISHER, TEST_ISBN, TEST_DESCRIPTION));
}
