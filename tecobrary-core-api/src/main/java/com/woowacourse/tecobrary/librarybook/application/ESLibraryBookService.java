//package com.woowacourse.tecobrary.librarybook.application;
//
//import com.woowacourse.tecobrary.librarybook.domain.ESLibraryBookRepository;
//import com.woowacourse.tecobrary.librarybook.ui.dto.LibraryBookResponseDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class ESLibraryBookService {
//
//    private final ESLibraryBookRepository esLibraryBookRepository;
//
//    @Autowired
//    public ESLibraryBookService(final ESLibraryBookRepository esLibraryBookRepository) {
//        this.esLibraryBookRepository = esLibraryBookRepository;
//    }
//
//    public List<LibraryBookResponseDto> searchLibraryBook(final String keyword, final int page, final int size) {
//        return esLibraryBookRepository.searchByKeyword(keyword, PageRequest.of(page - 1, size))
//                .stream()
//                .collect(Collectors.toList());
//    }
//}
