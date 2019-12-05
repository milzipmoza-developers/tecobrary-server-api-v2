package com.woowacourse.tecobrary.common.infra.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.woowacourse.tecobrary.common.infra.filter.CorsHeader.*;

public class CorsFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, ALLOW_ORIGIN);
        response.setHeader(ACCESS_CONTROL_ALLOW_METHODS, ALLOW_METHODS);
        response.setHeader(ACCESS_CONTROL_MAX_AGE, MAX_AGE);
        response.setHeader(ACCESS_CONTROL_ALLOW_HEADERS, AUTHORIZATION_CONTENT_TYPE);
        if (OPTIONS.equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        filterChain.doFilter(request, response);
    }
}
