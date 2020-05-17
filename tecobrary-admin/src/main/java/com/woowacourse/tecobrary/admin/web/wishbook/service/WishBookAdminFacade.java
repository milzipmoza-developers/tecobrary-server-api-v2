package com.woowacourse.tecobrary.admin.web.wishbook.service;

import com.woowacourse.tecobrary.admin.web.wishbook.converter.WishBookConverter;
import com.woowacourse.tecobrary.admin.web.wishbook.dto.*;
import com.woowacourse.tecobrary.domain.wishbook.entity.WishBook;
import com.woowacourse.tecobrary.web.tecorvis.api.SlackBotService;
import com.woowacourse.tecobrary.web.tecorvis.dto.SlackBotBookInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class WishBookAdminFacade {

    private final WishBookAdminService wishBookAdminService;
    private final WishBookConverter wishBookConverter;
    private final SlackBotService slackBotService;

    public Page<WishBookInfoResponse> getWishBooks(Pageable pageable, WishBookSearchRequest request) {
        Page<WishBook> wishbooks = wishBookAdminService.getWishBooks(pageable, request);

        List<WishBookInfoResponse> dtos = wishbooks.stream()
                .map(wishBookConverter::convertInfo)
                .collect(Collectors.toList());

        return new PageImpl<>(dtos, pageable, wishbooks.getTotalElements());
    }

    public WishBookHandleResponse enrollWishBook(boolean sendMessage, WishBookEnrollRequest request) {
        WishBook wishBook = wishBookAdminService.enrollWishBook(request);

        sendTecorvisMessage(sendMessage, wishBook);

        return WishBookHandleResponse.builder()
                .id(wishBook.getId())
                .status(wishBook.getStatus())
                .message("등록 성공")
                .build();
    }

    public WishBookHandleResponse cancelWishBook(WishBookCancelRequest request) {
        WishBook wishBook = wishBookAdminService.cancelWishBook(request);

        return WishBookHandleResponse.builder()
                .id(wishBook.getId())
                .status(wishBook.getStatus())
                .message("취소 성공")
                .build();
    }

    private void sendTecorvisMessage(boolean sendMessage, WishBook wishBook) {
        if (sendMessage && wishBook.isEnrolled()) {
            slackBotService.enrolledNotification(SlackBotBookInfoDto.builder()
                    .title(wishBook.getTitle())
                    .author(wishBook.getAuthor())
                    .publisher(wishBook.getPublisher())
                    .isbn(wishBook.getIsbn())
                    .createdAt(wishBook.getCreatedAt())
                    .build());
        }
    }
}
