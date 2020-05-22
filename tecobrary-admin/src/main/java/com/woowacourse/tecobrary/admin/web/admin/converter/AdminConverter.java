package com.woowacourse.tecobrary.admin.web.admin.converter;

import com.woowacourse.tecobrary.admin.web.admin.dto.AdminCreateResponse;
import com.woowacourse.tecobrary.admin.web.admin.dto.AdminSearchResponse;
import com.woowacourse.tecobrary.domain.admin.entity.Admin;
import org.springframework.stereotype.Component;

@Component
public class AdminConverter {

    public AdminSearchResponse convertToSearchResponse(Admin admin) {
        return AdminSearchResponse.builder()
                .id(admin.getId())
                .email(admin.getEmail())
                .name(admin.getName())
                .role(admin.getRole().name())
                .build();
    }

    public AdminCreateResponse convertToCreateResponse(Admin admin) {
        return AdminCreateResponse.builder()
                .id(admin.getId())
                .email(admin.getEmail())
                .build();
    }
}
