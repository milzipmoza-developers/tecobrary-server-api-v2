package com.woowacourse.tecobrary.common.infra.filter;

import com.woowacourse.tecobrary.user.infra.util.JwtUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class JwtFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION = "Authorization";
    private static final String DELIMITER = " ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String bearer = request.getHeader(AUTHORIZATION);
        if (isInvalidAuthorizationHeader(bearer)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        filterChain.doFilter(request, response);
    }

    private boolean isInvalidAuthorizationHeader(String bearer) {
        return Objects.isNull(bearer)
                || !bearer.startsWith("Bearer" + DELIMITER)
                || JwtUtils.isTokenExpired(bearer.substring(bearer.indexOf(DELIMITER)));
    }
}
