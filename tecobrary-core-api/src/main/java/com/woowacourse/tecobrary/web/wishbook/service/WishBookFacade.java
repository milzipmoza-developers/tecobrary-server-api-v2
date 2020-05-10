package com.woowacourse.tecobrary.web.wishbook.service;

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

    private final WishBookRepository wishBookRepository;
    private final UserRepository userRepository;
    private final WishBookConverter wishBookConverter;
    private final SlackBotService slackBotService;

    @Transactional
    public WishBookInfoDto createWishBook(final WishBookInfoDto wishBookInfoDto) {
        checkDuplicated(wishBookInfoDto);

        User user = userRepository.findById(wishBookInfoDto.getUserId())
                .orElseThrow(() -> new WishBookRequestUserNotFoundException(wishBookInfoDto.getUserId()));

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
            throw new WishBookAlreadyExistException(wishBookInfoDto.getIsbn());
        }
    }
}
