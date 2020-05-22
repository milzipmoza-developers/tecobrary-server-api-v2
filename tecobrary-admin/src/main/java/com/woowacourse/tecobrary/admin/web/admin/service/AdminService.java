package com.woowacourse.tecobrary.admin.web.admin.service;

import com.woowacourse.tecobrary.admin.web.admin.dto.AdminCreateRequest;
import com.woowacourse.tecobrary.admin.web.admin.dto.AdminSearchRequest;
import com.woowacourse.tecobrary.admin.web.admin.repository.AdminManageRepository;
import com.woowacourse.tecobrary.admin.web.admin.repository.AdminSearchClause;
import com.woowacourse.tecobrary.domain.admin.entity.Admin;
import com.woowacourse.tecobrary.domain.admin.entity.AdminRole;
import com.woowacourse.tecobrary.domain.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final AdminManageRepository adminManageRepository;

    public Page<Admin> searchAdmin(Pageable pageable, AdminSearchRequest request) {
        AdminSearchClause clause = AdminSearchClause.builder()
                .name(request.getName())
                .email(request.getEmail())
                .build();

        return adminManageRepository.searchAdmin(pageable, clause);
    }

    public Admin createAdmin(AdminCreateRequest request) {
        Admin admin = Admin.builder()
                .email(request.getEmail())
                .name("임시 이름")
                .picture("임시 picture")
                .build();
        admin.updateRole(AdminRole.ROLE_ADMIN);
        return adminRepository.save(admin);
    }
}
