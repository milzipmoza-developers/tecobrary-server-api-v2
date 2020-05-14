package com.woowacourse.tecobrary.admin.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin("*")
public class AdminUserInfoController {

    @GetMapping("/admin/user/info")
    public AdminUserDto userInfo() {
        return (AdminUserDto) Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .orElseThrow(() -> new IllegalArgumentException("로그인이 필요합니다"));
    }
}
