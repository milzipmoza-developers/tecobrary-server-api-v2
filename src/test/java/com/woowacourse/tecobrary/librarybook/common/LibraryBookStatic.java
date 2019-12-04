package com.woowacourse.tecobrary.librarybook.common;

import com.woowacourse.tecobrary.common.model.BookCoverUrl;
import com.woowacourse.tecobrary.common.model.BookInfo;
import com.woowacourse.tecobrary.librarybook.domain.LibraryBook;
import com.woowacourse.tecobrary.librarybook.ui.dto.LibraryBookEnrollDto;

import java.time.LocalDateTime;

public interface LibraryBookStatic {

    String TEST_IMAGE = "https://image.url";
    String TEST_TITLE = "제목";
    String TEST_AUTHOR = "작가";
    String TEST_PUBLISHER = "출판사";
    String TEST_ISBN = "19930705";
    String TEST_DESCRIPTION = "내용 없음";

    Long TEST_LIBRARY_BOOK_ID = 1L;
    String TEST_LIBRARY_BOOK_IMAGE = "https://bookthumb-phinf.pstatic.net/cover/091/459/09145968.jpg?type=m1&udate=20171011";
    String TEST_LIBRARY_BOOK_TITLE = "객체지향의 사실과 오해 (역할, 책임, 협력 관점에서 본 객체지향)";
    String TEST_LIBRARY_BOOK_AUTHOR = "조영호";
    String TEST_LIBRARY_BOOK_PUBLISHER = "위키북스";
    String TEST_LIBRARY_BOOK_ISBN = "8998139766 9788998139766";
    String TEST_LIBRARY_BOOK_DESCRIPTION = "객체지향에 대한 선입견을 버려라!『객체지향의 사실과 오해』는 객체지향이란 무엇인가라는 원론적면서도 다소 위험한 질문에 답하기 위해 쓰여진 책이다. 안타깝게도 많은 사람들이 객체지향의 본질을 오해하고 있다. 가장 널리 퍼져있는 오해는 클래스가 객체지향 프로그래밍의 중심이라는 것이다.... ";

    Long TEST_LIBRARY_BOOK_ID_02 = 2L;
    String TEST_LIBRARY_BOOK_IMAGE_02 = "https://bookthumb-phinf.pstatic.net/cover/073/336/07333658.jpg?type=m1&udate=20190204";
    String TEST_LIBRARY_BOOK_TITLE_02 = "자바 성능 튜닝 이야기 (개발자가 반드시 알아야 할)";
    String TEST_LIBRARY_BOOK_AUTHOR_02 = "이상민";
    String TEST_LIBRARY_BOOK_PUBLISHER_02 = "인사이트";
    String TEST_LIBRARY_BOOK_ISBN_02 = "8966260926 9788966260928";
    String TEST_LIBRARY_BOOK_DESCRIPTION_02 = "『자바 성능 튜닝 이야기』는 고성능 애플리케이션을 위해 고려 해야 할 복잡한 요소들을 하나하나 짚어 주는 책이다. 장애를 일으키는 반복적인 코딩 이슈부터 시스템 진단, 튜닝 방법에 이르기까지 성능 개선이 필요한 핵심 정보만을 담아 정리하였으며 수년간에 걸친 경험적 사례를 토대로 실무에 적용... ";

    Long TEST_LIBRARY_BOOK_ID_19 = 19L;
    String TEST_LIBRARY_BOOK_IMAGE_19 = "https://bookthumb-phinf.pstatic.net/cover/076/231/07623127.jpg?type=m1&udate=20150715";
    String TEST_LIBRARY_BOOK_TITLE_19 = "열혈강의 자바 웹 개발 워크북 (MVC 아키텍처 마이바티스 스프링으로 만드는 실무형 개발자 로드맵)";
    String TEST_LIBRARY_BOOK_AUTHOR_19 = "엄진영";
    String TEST_LIBRARY_BOOK_PUBLISHER_19 = "프리렉";
    String TEST_LIBRARY_BOOK_ISBN_19 = "8965400678 9788965400677";
    String TEST_LIBRARY_BOOK_DESCRIPTION_19 = "이 책은 자바 웹 개발 기초에서 프레임워크를 사용하는 실무 내용까지 다루고 있다. JSP에 한정한 웹 프로그래밍이 아니라 변화한 현재의 동적인 웹 개발 환경에 맞도록 서블릿/JSP를 기본으로 웹 개발에 필요한 기술들을 설명하고 있다.";

    Long TEST_LIBRARY_BOOK_ID_07 = 7L;
    String TEST_LIBRARY_BOOK_IMAGE_07 = "https://bookthumb-phinf.pstatic.net/cover/073/174/07317474.jpg?type=m1&udate=20190216";
    String TEST_LIBRARY_BOOK_TITLE_07 = "코딩을 지탱하는 기술 (원리로 깨우치는 프로그래밍 기법)";
    String TEST_LIBRARY_BOOK_AUTHOR_07 = "니시오 히로카즈";
    String TEST_LIBRARY_BOOK_PUBLISHER_07 = "비제이퍼블릭";
    String TEST_LIBRARY_BOOK_ISBN_07 = "8994774483 9788994774480";
    String TEST_LIBRARY_BOOK_DESCRIPTION_07 = "『코딩을 지탱하는 기술』는 프로그램이 언어가 가지고 있는 다양한 개념이 왜 존재하고 있는지를 설명해주고 있다. 저자는 언어 설계자의 관점에서 여러 언어를 비교하고 언어가 어떻게 변화되어 왔는지 설명한다. 더불어 다양한 개념이 왜 탄생하게 되었는지 이해할 수 있으며 각 언어를 왜, 언제, 어떻게... ";

    String TEST_LIBRARY_BOOK_IMAGE_99 = "https://bookthumb-phinf.pstatic.net/cover/101/607/10160776.jpg?type=m1&udate=20190216";
    String TEST_LIBRARY_BOOK_TITLE_99 = "SQL 레벨업 (DB 성능 최적화를 위한 SQL 실전 가이드)";
    String TEST_LIBRARY_BOOK_AUTHOR_99 = "미크";
    String TEST_LIBRARY_BOOK_PUBLISHER_99 = "한빛미디어";
    String TEST_LIBRARY_BOOK_ISBN_99 = "8968482519 9788968482519";
    String TEST_LIBRARY_BOOK_DESCRIPTION_99 = "『SQL 레벨업』은 《SQL 첫걸음》으로 성공적인 입문을 마치고, 다음 고지를 바라보는 이들을 위한 책이다. 고성능 SQL 작성 방법을 초보자 눈높이에 맞춰 다양한 예제를 통해 설명한다. 특히 오라클과 호환성을 목표로 하는 오픈소스인 POSTGRESQL을 사용하여 모든 예제를 작성했고, 둘의 수행 결과가 상이한... ";

    LibraryBook TEST_LIBRARY_BOOK = new LibraryBook(new BookCoverUrl(TEST_IMAGE),
            new BookInfo(TEST_TITLE, TEST_AUTHOR, TEST_PUBLISHER, TEST_ISBN, TEST_DESCRIPTION));

    LibraryBook TEST_LIBRARY_BOOK_01 = new LibraryBook(new BookCoverUrl(TEST_LIBRARY_BOOK_IMAGE),
            new BookInfo(TEST_LIBRARY_BOOK_TITLE, TEST_LIBRARY_BOOK_AUTHOR, TEST_LIBRARY_BOOK_PUBLISHER,
                    TEST_LIBRARY_BOOK_ISBN, TEST_LIBRARY_BOOK_DESCRIPTION));

    LibraryBook TEST_LIBRARY_BOOK_02 = new LibraryBook(new BookCoverUrl(TEST_LIBRARY_BOOK_IMAGE_02),
            new BookInfo(TEST_LIBRARY_BOOK_TITLE_02, TEST_LIBRARY_BOOK_AUTHOR_02, TEST_LIBRARY_BOOK_PUBLISHER_02,
                    TEST_LIBRARY_BOOK_ISBN_02, TEST_LIBRARY_BOOK_DESCRIPTION_02));

    LibraryBook TEST_LIBRARY_BOOK_19 = new LibraryBook(new BookCoverUrl(TEST_LIBRARY_BOOK_IMAGE_19),
            new BookInfo(TEST_LIBRARY_BOOK_TITLE_19, TEST_LIBRARY_BOOK_AUTHOR_19, TEST_LIBRARY_BOOK_PUBLISHER_19,
                    TEST_LIBRARY_BOOK_ISBN_19, TEST_LIBRARY_BOOK_DESCRIPTION_19));

    LibraryBook TEST_LIBRARY_BOOK_07 = new LibraryBook(new BookCoverUrl(TEST_LIBRARY_BOOK_IMAGE_07),
            new BookInfo(TEST_LIBRARY_BOOK_TITLE_07, TEST_LIBRARY_BOOK_AUTHOR_07, TEST_LIBRARY_BOOK_PUBLISHER_07,
                    TEST_LIBRARY_BOOK_ISBN_07, TEST_LIBRARY_BOOK_DESCRIPTION_07));

    LibraryBookEnrollDto TEST_LIBRARY_BOOK_ENROLL_DTO_99 = LibraryBookEnrollDto.builder()
            .image(TEST_LIBRARY_BOOK_IMAGE_99)
            .title(TEST_LIBRARY_BOOK_TITLE_99)
            .author(TEST_LIBRARY_BOOK_AUTHOR_99)
            .publisher(TEST_LIBRARY_BOOK_PUBLISHER_99)
            .isbn(TEST_LIBRARY_BOOK_ISBN_99)
            .description(TEST_LIBRARY_BOOK_DESCRIPTION_99)
            .enrolledDate(LocalDateTime.now())
            .build();
}
