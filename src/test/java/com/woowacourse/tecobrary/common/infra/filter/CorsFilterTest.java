package com.woowacourse.tecobrary.common.infra.filter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.woowacourse.tecobrary.common.infra.filter.CorsHeader.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class CorsFilterTest {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private FilterChain filterChain;

    @BeforeEach
    void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        filterChain = mock(FilterChain.class);
    }

    @DisplayName("Request Method 가 OPTIONS 면 CORS 관련 Response Header 를 설정한다.")
    @Test
    public void corsFilterWhenRequestMethodIsOptions() throws ServletException, IOException {
        request.setMethod(OPTIONS);

        CorsFilter corsFilter = new CorsFilter();
        corsFilter.doFilterInternal(request, response, filterChain);

        assertEquals(response.getStatus(), HttpServletResponse.SC_OK);
        assertEquals(response.getHeader(ACCESS_CONTROL_ALLOW_ORIGIN), ALLOW_ORIGIN);
        assertEquals(response.getHeader(ACCESS_CONTROL_ALLOW_METHODS), ALLOW_METHODS);
        assertEquals(response.getHeader(ACCESS_CONTROL_MAX_AGE), MAX_AGE);
        assertEquals(response.getHeader(ACCESS_CONTROL_ALLOW_HEADERS), AUTHORIZATION_CONTENT_TYPE);
    }
}