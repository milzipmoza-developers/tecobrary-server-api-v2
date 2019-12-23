package com.woowacourse.tecobrary.common.application.api;

import com.woowacourse.tecobrary.librarybook.ui.dto.LibraryBookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class SlackBotWebClient {

    private static final String REQUEST_SCHEME = "http";
    private static final String SLACKBOT_WISH_BOOK_NOTIFICATION_REQUEST_PATH = "/registered";
    private static final String SLACKBOT_ENROLLED_NOTIFICATION_REQUEST_PATH = "/enrolled";
    private static final int REQUEST_PORT = 5000;

    private String host;

    @Autowired
    public SlackBotWebClient(@Value("${slackbot.host}") final String host) {
        this.host = host;
    }

    public String wishBookNotification(final LibraryBookDto libraryBookDto) {
        return WebClient
                .create()
                .post()
                .uri(uriBuilder ->
                        uriBuilder.scheme(REQUEST_SCHEME)
                                .host(host)
                                .port(5000)
                                .path(SLACKBOT_WISH_BOOK_NOTIFICATION_REQUEST_PATH)
                                .build())
                .body(BodyInserters.fromObject(libraryBookDto))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String enrolledNotification(final LibraryBookDto libraryBookDto) {
        return WebClient
                .create()
                .post()
                .uri(uriBuilder ->
                        uriBuilder.scheme(REQUEST_SCHEME)
                                .host(host)
                                .port(REQUEST_PORT)
                                .path(SLACKBOT_ENROLLED_NOTIFICATION_REQUEST_PATH)
                                .build())
                .body(BodyInserters.fromObject(libraryBookDto))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
