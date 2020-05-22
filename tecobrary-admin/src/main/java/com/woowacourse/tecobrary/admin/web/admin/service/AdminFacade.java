package com.woowacourse.tecobrary.admin.web.admin.service;

import com.woowacourse.tecobrary.admin.web.admin.converter.AdminConverter;
import com.woowacourse.tecobrary.admin.web.admin.dto.AdminCreateRequest;
import com.woowacourse.tecobrary.admin.web.admin.dto.AdminCreateResponse;
import com.woowacourse.tecobrary.admin.web.admin.dto.AdminSearchRequest;
import com.woowacourse.tecobrary.admin.web.admin.dto.AdminSearchResponse;
import com.woowacourse.tecobrary.domain.admin.entity.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminFacade {

    private final AdminService adminService;
    private final AdminConverter adminConverter;

    public Page<AdminSearchResponse> searchAdmin(Pageable pageable, AdminSearchRequest request) {
        Page<Admin> admins = adminService.searchAdmin(pageable, request);

        List<AdminSearchResponse> dtos = admins.stream()
                .map(adminConverter::convertToSearchResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(dtos, pageable, admins.getTotalElements());
    }

    public AdminCreateResponse createAdmin(AdminCreateRequest request) {
        Admin admin = adminService.createAdmin(request);
        return adminConverter.convertToCreateResponse(admin);
    }
}
