package com.woowacourse.tecobrary.wishbook.command.application;

import com.woowacourse.tecobrary.wishbook.command.domain.ExistWishBookIsbnException;
import com.woowacourse.tecobrary.wishbook.command.domain.NotFoundWishBookException;
import com.woowacourse.tecobrary.wishbook.command.domain.WishBook;
import com.woowacourse.tecobrary.wishbook.command.domain.WishBookRepository;
import com.woowacourse.tecobrary.wishbook.command.util.WishBookInfoDtoMapper;
import com.woowacourse.tecobrary.wishbook.ui.dto.WishBookInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class WishBookService {

    private final WishBookRepository wishBookRepository;

    @Autowired
    public WishBookService(final WishBookRepository wishBookRepository) {
        this.wishBookRepository = wishBookRepository;
    }

    public List<WishBookInfoDto> findWishBooksOnPage(final int page, final int number) {

        Page<WishBook> pageWishBooks = wishBookRepository.findAll(PageRequest.of(page - 1, number));

        return pageWishBooks.getContent()
                .stream()
                .map(WishBookInfoDtoMapper::toDto)
                .collect(toList());
    }

    public Long createWishBook(final WishBookInfoDto wishBookInfoDto) {
        if (wishBookRepository.existsByWishBookInfoIsbn(wishBookInfoDto.getIsbn())) {
            throw new ExistWishBookIsbnException();
        }
        WishBook wishBook = WishBookInfoDtoMapper.toEntity(wishBookInfoDto);
        return wishBookRepository.save(wishBook).getId();
    }

    public WishBookInfoDto findById(final Long id) {
        WishBook wishBook = wishBookRepository.findById(id).orElseThrow(NotFoundWishBookException::new);
        return WishBookInfoDtoMapper.toDto(wishBook);
    }

    public void deleteWishBook(final Long id) {
        if (!existsById(id)) {
            throw new NotFoundWishBookException();
        }

        wishBookRepository.deleteById(id);
    }

    public boolean existsById(final Long id) {
        return wishBookRepository.existsById(id);
    }
}
