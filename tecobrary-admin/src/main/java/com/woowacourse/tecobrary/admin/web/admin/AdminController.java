package com.woowacourse.tecobrary.admin.web.admin;

import com.woowacourse.tecobrary.admin.web.admin.dto.AdminCreateRequest;
import com.woowacourse.tecobrary.admin.web.admin.dto.AdminCreateResponse;
import com.woowacourse.tecobrary.admin.web.admin.dto.AdminSearchRequest;
import com.woowacourse.tecobrary.admin.web.admin.dto.AdminSearchResponse;
import com.woowacourse.tecobrary.admin.web.admin.service.AdminFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminFacade adminFacade;

    @GetMapping
    public Page<AdminSearchResponse> searchAdmin(@PageableDefault Pageable pageable,
                                                 @Valid AdminSearchRequest request) {
        return adminFacade.searchAdmin(pageable, request);
    }

    @PostMapping
    public AdminCreateResponse createAdmin(@RequestBody AdminCreateRequest request) {
        return adminFacade.createAdmin(request);
    }
}
