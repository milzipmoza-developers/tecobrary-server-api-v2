package com.woowacourse.tecobrary.wishbook.common;

import com.woowacourse.tecobrary.common.model.BookCoverUrl;
import com.woowacourse.tecobrary.common.model.BookInfo;
import com.woowacourse.tecobrary.wishbook.command.domain.WishBook;
import com.woowacourse.tecobrary.wishbook.command.domain.WishBookRequestUser;
import com.woowacourse.tecobrary.wishbook.command.util.WishBookInfoDtoMapper;
import com.woowacourse.tecobrary.wishbook.ui.dto.WishBookInfoDto;

public interface WishBookStatic {

    String TEST_COVER_URL = "https://bookthumb-phinf.pstatic.net/cover/101/607/10160776.jpg?type=m1&udate=20190216";
    String TEST_TITLE = "SQL 레벨업 (DB 성능 최적화를 위한 SQL 실전 가이드)";
    String TEST_AUTHOR = "미크";
    String TEST_PUBLISHER = "한빛미디어";
    String TEST_ISBN = "8968482519 9788968482519";
    String TEST_DESCRIPTION = "『SQL 레벨업』은 《SQL 첫걸음》으로 성공적인 입문을 마치고, 다음 고지를 바라보는 이들을 위한 책이다. 고성능 SQL 작성 방법을 초보자 눈높이에 맞춰 다양한 예제를 통해 설명한다. 특히 오라클과 호환성을 목표로 하는 오픈소스인 POSTGRESQL을 사용하여 모든 예제를 작성했고, 둘의 수행 결과가 상이한... ";
    Long TEST_USER_ID = 2L;

    String TEST_COVER_URL_01 = "https://bookthumb-phinf.pstatic.net/cover/107/412/10741267.jpg?type=m1&udate=20160704";
    String TEST_TITLE_01 = "팟캐스트 나는 프로그래머다 2 (알맹이만 쏙쏙, 방송보다 더 밀도 높게 더 유용하게)";
    String TEST_AUTHOR_01 = "임백준|정도현|김호광";
    String TEST_PUBLISHER_01 = "한빛미디어";
    String TEST_ISBN_01 = "896848290X 9788968482908";
    String TEST_DESCRIPTION_01 = "서울, 도쿄, 뉴욕에 사는 프로그래머가 수다를 떨기 위해서 모였다. 보안 전문가 데니스, 도쿄의 아키텍트 정개발, 그리고 뉴욕의 프로그래머 임작가가 새로운 패러다임과 기술을 소개하고, 프로그래머들의 삶과 애환을 이야기한다. 여러 분야의 전문가를 초대해서 웃음과 배움이 넘치는 대화를 나누고, 한국의... ";
    Long TEST_USER_ID_01 = 12L;

    String TEST_COVER_URL_02 = "https://bookthumb-phinf.pstatic.net/cover/158/717/15871738.jpg?type=m1&udate=20191126";
    String TEST_TITLE_02 = "스프링 부트와 AWS로 혼자 구현하는 웹 서비스 (인텔리제이, JPA, JUnit 테스트, 그레이들)";
    String TEST_AUTHOR_02 = "이동욱";
    String TEST_PUBLISHER_02 = "프리렉";
    String TEST_ISBN_02 = "8965402603 9788965402602";
    String TEST_DESCRIPTION_02 = "경험이 실력이 되는 순간!이 책은 제목 그대로 스프링 부트와 AWS로 웹 서비스를 구현합니다. JPA와 JUNIT 테스트, 그레이들, 머스테치, 스프링 시큐리티를 활용한 소셜 로그인 등으로 애플리케이션을 개발하고, 뒤이어 AWS 인프라의 기본 사용법과 AWS EC2와 RDS를 사용해 서비스가 가능하도록 합니다. 이렇게... ";
    Long TEST_USER_ID_02 = 23L;

    String TEST_CREATE_ISBN = "0123456";

    BookCoverUrl TEST_BOOK_COVER_URL = new BookCoverUrl(TEST_COVER_URL);
    BookCoverUrl TEST_BOOK_COVER_URL_01 = new BookCoverUrl(TEST_COVER_URL_01);
    BookCoverUrl TEST_BOOK_COVER_URL_02 = new BookCoverUrl(TEST_COVER_URL_02);

    BookInfo TEST_BOOK_INFO = new BookInfo(TEST_TITLE, TEST_AUTHOR, TEST_PUBLISHER, TEST_ISBN, TEST_DESCRIPTION);
    BookInfo TEST_BOOK_INFO_01 = new BookInfo(TEST_TITLE_01, TEST_AUTHOR_01, TEST_PUBLISHER_01, TEST_ISBN_01, TEST_DESCRIPTION_01);
    BookInfo TEST_BOOK_INFO_02 = new BookInfo(TEST_TITLE_02, TEST_AUTHOR_02, TEST_PUBLISHER_02, TEST_ISBN_02, TEST_DESCRIPTION_02);
    BookInfo TEST_CREATE_BOOK_INFO = new BookInfo(TEST_TITLE_01, TEST_AUTHOR_01, TEST_PUBLISHER_01, TEST_CREATE_ISBN, TEST_DESCRIPTION_01);

    WishBookRequestUser TEST_REQUEST_USER = new WishBookRequestUser(TEST_USER_ID);
    WishBookRequestUser TEST_REQUEST_USER_01 = new WishBookRequestUser(TEST_USER_ID_01);
    WishBookRequestUser TEST_REQUEST_USER_02 = new WishBookRequestUser(TEST_USER_ID_02);

    WishBook TEST_WISH_BOOK = new WishBook(TEST_BOOK_COVER_URL, TEST_BOOK_INFO, TEST_REQUEST_USER);
    WishBook TEST_WISH_BOOK_01 = new WishBook(TEST_BOOK_COVER_URL_01, TEST_BOOK_INFO_01, TEST_REQUEST_USER_01);
    WishBook TEST_WISH_BOOK_02 = new WishBook(TEST_BOOK_COVER_URL_02, TEST_BOOK_INFO_02, TEST_REQUEST_USER_02);
    WishBook TEST_CREATE_WISH_BOOK = new WishBook(TEST_BOOK_COVER_URL_01, TEST_CREATE_BOOK_INFO, TEST_REQUEST_USER_01);

    WishBookInfoDto TEST_WISH_BOOK_INFO_DTO = WishBookInfoDtoMapper.toDto(TEST_WISH_BOOK);
}
