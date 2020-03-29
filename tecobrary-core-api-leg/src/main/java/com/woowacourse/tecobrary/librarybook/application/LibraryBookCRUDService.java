package com.woowacourse.tecobrary.librarybook.application;

import com.woowacourse.tecobrary.librarybook.entity.LibraryBook;
import com.woowacourse.tecobrary.librarybook.ui.dto.LibraryBookCreateResponseDto;
import com.woowacourse.tecobrary.librarybook.ui.dto.LibraryBookDto;
import com.woowacourse.tecobrary.librarybook.ui.dto.LibraryBookResponseDto;
import com.woowacourse.tecobrary.librarybook.ui.dto.LibraryBookTotalCountResponseDto;
import com.woowacourse.tecobrary.librarybook.util.BookConverter;
import com.woowacourse.tecobrary.librarybook.util.LibraryBookMapper;
import com.woowacourse.tecobrary.web.tecorvis.api.SlackBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryBookCRUDService {

    private LibraryBookService libraryBookService;
    private SlackBotService slackBotService;

    @Autowired
    public LibraryBookCRUDService(final LibraryBookService libraryBookService, final SlackBotService slackBotService) {
        this.libraryBookService = libraryBookService;
        this.slackBotService = slackBotService;
    }

    public LibraryBookCreateResponseDto save(final LibraryBookDto libraryBookDto) {
        LibraryBook savedLibraryBook = libraryBookService.save(libraryBookDto);
        slackBotService.enrolledNotification(BookConverter.convert(savedLibraryBook));
        return new LibraryBookCreateResponseDto(savedLibraryBook.getId(), savedLibraryBook.getTitle() + " register succeed");
    }

    public LibraryBookTotalCountResponseDto count() {
        return new LibraryBookTotalCountResponseDto(libraryBookService.count());
    }

    public LibraryBookResponseDto findById(final Long id) {
        return LibraryBookMapper.toResponseDto(libraryBookService.findById(id));
    }

    public List<LibraryBookResponseDto> findAll(final int page, final int number) {
        return libraryBookService.findAll(page, number)
                .stream()
                .map(LibraryBookMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<LibraryBookResponseDto> findAllByTitleContaining(final String title, final int page, final int number) {
        return libraryBookService.findAllByTitleContaining(title, page, number)
                .stream()
                .map(LibraryBookMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
