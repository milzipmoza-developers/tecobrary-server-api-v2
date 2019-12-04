package com.woowacourse.tecobrary.wishbook.command.application;

import com.woowacourse.tecobrary.wishbook.command.domain.WishBook;
import com.woowacourse.tecobrary.wishbook.command.domain.WishBookRepository;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class WishBookServiceTest implements WishBookStatic {

    @Mock
    private WishBookRepository wishBookRepository;

    @InjectMocks
    private WishBookService wishBookService;

    @DisplayName("특정 페이지의 wishBook list를 조회한다.")
    @Test
    void successfullyFindWishBooksOnPage() {
        List<WishBook> mockWishBooks = Arrays.asList(TEST_WISHBOOK, TEST_WISHBOOK_01);

        given(wishBookRepository.findAll(any(PageRequest.class)))
                .willReturn(new PageImpl<>(mockWishBooks, PageRequest.of(1, 2), 2));

        List<WishBookInfoDto> wishBooks = wishBookService.findWishBooksOnPage(1, 2);
        assertThat(wishBooks).hasSize(2);
    }
}