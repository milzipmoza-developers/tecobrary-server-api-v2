package com.woowacourse.tecobrary.common.infra.filter;

import com.woowacourse.tecobrary.user.infra.util.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@PrepareForTest({ JwtUtils.class })
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JwtFilterTest {

    private static final String EXPIRED_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
            "eyJpZCI6IjEiLCJlbWFpbCI6Imx1ZmZ5QG1pbHppcGRldnMuY29tIiwibmFtZSI6Imx1ZmZ5IiwiYXV0aG9yaXphdGlvbiI6IktJTkciLCJhdmF0YXJVcmwiOiJodHRwczovL2F2YXRhci51cmwvMTIzIiwiaWF0IjoxNTcxMjI2MzYzLCJleHAiOjE1NzEyMjYzNjV9." +
            "x7LIWVJguaeqbPmYCxdyMHTbGM5N7yhxhIpdzysFxpg"; // signature secret : test

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private FilterChain filterChain;
    private JwtFilter jwtFilter;

    @BeforeEach
    void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        filterChain = mock(FilterChain.class);
        jwtFilter = new JwtFilter();
    }

    @DisplayName("비어있는 Request Header 의 Authorization 에 대하여 Forbidden 을 응답한다.")
    @Test
    void requestAuthorizationHeaderIsNull() throws ServletException, IOException {
        jwtFilter.doFilterInternal(request, response, filterChain);
        assertEquals(response.getStatus(), HttpServletResponse.SC_FORBIDDEN);
    }

    @DisplayName("적절하지 않은 형식의 Request Header 의 Authorization 에 대하여 Forbidden 을 응답한다.")
    @Test
    void requestAuthorizationHeaderHasNoBearerPrefix() throws ServletException, IOException {
        request.addHeader("Authorization", "this is not valid header");
        jwtFilter.doFilterInternal(request, response, filterChain);
        assertEquals(response.getStatus(), HttpServletResponse.SC_FORBIDDEN);
    }

    @DisplayName("공백 구분자가 없는 Request Header 의 Authorization 에 대하여 Forbidden 을 응답한다.")
    @Test
    void requestAuthorizationHeaderHasNoDelimiter() throws ServletException, IOException {
        request.addHeader("Authorization", "Bearer" + EXPIRED_TOKEN);
        jwtFilter.doFilterInternal(request, response, filterChain);
        assertEquals(response.getStatus(), HttpServletResponse.SC_FORBIDDEN);
    }
}