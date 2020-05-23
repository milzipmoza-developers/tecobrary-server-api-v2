package com.woowacourse.tecobrary.web.wishbook.service;

import com.woowacourse.tecobrary.domain.librarybook.repository.LibraryBookRepository;
import com.woowacourse.tecobrary.domain.user.entity.User;
import com.woowacourse.tecobrary.domain.user.repository.UserRepository;
import com.woowacourse.tecobrary.web.tecorvis.api.SlackBotService;
import com.woowacourse.tecobrary.web.tecorvis.dto.SlackBotBookInfoDto;
import com.woowacourse.tecobrary.web.wishbook.converter.WishBookConverter;
import com.woowacourse.tecobrary.web.wishbook.dto.WishBookInfoDto;
import com.woowacourse.tecobrary.web.wishbook.exception.WishBookAlreadyExistException;
import com.woowacourse.tecobrary.web.wishbook.exception.WishBookRequestUserNotFoundException;
import com.woowacourse.tecobrary.domain.wishbook.entity.WishBook;
import com.woowacourse.tecobrary.domain.wishbook.repository.WishBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class WishBookFacade {

    private static final String DUPLICATED_WISH_BOOK_ISBN_EXCEPTION_MESSAGE = "이미 희망도서에 등록되어 있습니다.";
    private static final String ALREADY_ENROLLED_BOOK_EXCEPTION_MESSAGE = "이미 등록된 장서 입니다.";

    private final WishBookRepository wishBookRepository;
    private final LibraryBookRepository libraryBookRepository;
    private final UserRepository userRepository;
    private final WishBookConverter wishBookConverter;
    private final SlackBotService slackBotService;

    @Transactional
    public WishBookInfoDto createWishBook(final WishBookInfoDto wishBookInfoDto) {
        checkDuplicated(wishBookInfoDto);

        User user = userRepository.findById(wishBookInfoDto.getUserId())
                .orElseThrow(() -> new WishBookRequestUserNotFoundException(wishBookInfoDto.getUserId()));

        if (libraryBookRepository.existsByIsbn(wishBookInfoDto.getIsbn())) {
            throw new WishBookAlreadyExistException(wishBookInfoDto.getIsbn(), ALREADY_ENROLLED_BOOK_EXCEPTION_MESSAGE);
        }

        WishBook wishBook = wishBookRepository.save(WishBook.builder()
                .title(wishBookInfoDto.getTitle())
                .image(wishBookInfoDto.getImage())
                .author(wishBookInfoDto.getAuthor())
                .isbn(wishBookInfoDto.getIsbn())
                .publisher(wishBookInfoDto.getPublisher())
                .description(wishBookInfoDto.getDescription())
                .user(user)
                .build());

        slackBotService.wishBookNotification(SlackBotBookInfoDto.builder()
                .title(wishBook.getTitle())
                .author(wishBook.getAuthor())
                .publisher(wishBook.getPublisher())
                .isbn(wishBook.getIsbn())
                .createdAt(wishBook.getCreatedAt())
                .build());

        return wishBookConverter.convertInfoDto(wishBook);
    }

    private void checkDuplicated(final WishBookInfoDto wishBookInfoDto) {
        if (wishBookRepository.existsByIsbn(wishBookInfoDto.getIsbn())) {
            throw new WishBookAlreadyExistException(wishBookInfoDto.getIsbn(), DUPLICATED_WISH_BOOK_ISBN_EXCEPTION_MESSAGE);
        }
    }
}
