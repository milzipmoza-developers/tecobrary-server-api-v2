package com.woowacourse.tecobrary.wishbook.command.application;

import com.woowacourse.tecobrary.wishbook.command.util.WishBookInfoDtoMapper;
import com.woowacourse.tecobrary.wishbook.domain.WishBook;
import com.woowacourse.tecobrary.wishbook.domain.WishBookRepository;
import com.woowacourse.tecobrary.wishbook.ui.dto.WishBookInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WishBookService {

    private final WishBookRepository wishBookRepository;

    @Autowired
    public WishBookService(final WishBookRepository wishBookRepository) {
        this.wishBookRepository = wishBookRepository;
    }

    public List<WishBook> findWishBooksOnPage(final int page, final int number) {
        return wishBookRepository.findAllByDeletedAtNull(PageRequest.of(page - 1, number))
                .stream()
                .collect(Collectors.toList());
    }

    @Transactional
    public WishBook createWishBook(final WishBookInfoDto wishBookInfoDto) {
        checkDuplicated(wishBookInfoDto);

        WishBook wishBook = WishBookInfoDtoMapper.toEntity(wishBookInfoDto);
        return wishBookRepository.save(wishBook);
    }

    private void checkDuplicated(final WishBookInfoDto wishBookInfoDto) {
        if (wishBookRepository.existsByWishBookInfoIsbn(wishBookInfoDto.getIsbn())) {
            throw new DuplicatedWishBookIsbnException();
        }
    }

    public WishBook findById(final Long id) {
        return getWishBook(id);
    }

    private WishBook getWishBook(final Long id) {
        return wishBookRepository.findById(id)
                .orElseThrow(NotFoundWishBookException::new);
    }

    public WishBook findNotEnrolledById(final Long id) {
        return findByIdNotSoftDeleted(id)
                .orElseThrow(NotFoundWishBookException::new);
    }

    private Optional<WishBook> findByIdNotSoftDeleted(final Long id) {
        return wishBookRepository.findByIdAndDeletedAtNull(id);
    }

    public WishBook findEnrolledById(final Long id) {
        return findByIdSoftDeleted(id)
                .orElseThrow(NotFoundWishBookException::new);
    }

    private Optional<WishBook> findByIdSoftDeleted(final Long id) {
        return wishBookRepository.findByIdAndDeletedAtNotNull(id);
    }

    @Transactional
    public WishBook softDeleteById(final Long id) {
        checkNotSoftDeleted(id);
        return softDelete(id);
    }

    private void checkNotSoftDeleted(final Long id) {
        if (!existsByIdAndSoftDeleted(id)) {
            throw new AlreadySoftDeletedWishBookException();
        }
    }

    private boolean existsByIdAndSoftDeleted(final Long id) {
        return wishBookRepository.existsByIdAndDeletedAtNotNull(id);
    }

    private WishBook softDelete(final Long id) {
        WishBook wishBook = getWishBook(id);
        wishBook.softDelete();
        return wishBook;
    }

    @Transactional
    public void deleteWishBook(final Long id) {
        checkNotExistWishBook(id);
        wishBookRepository.deleteById(id);
    }

    private void checkNotExistWishBook(final Long id) {
        if (!wishBookRepository.existsById(id)) {
            throw new NotFoundWishBookException();
        }
    }
}
