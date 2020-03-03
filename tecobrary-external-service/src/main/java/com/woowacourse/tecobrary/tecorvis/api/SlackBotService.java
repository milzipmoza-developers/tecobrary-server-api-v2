package com.woowacourse.tecobrary.tecorvis.api;

import com.woowacourse.tecobrary.tecorvis.dto.SlackBotBookInfoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlackBotService {

    private static final Logger log = LoggerFactory.getLogger(SlackBotService.class);

    private SlackBotWebClient slackBotWebClient;

    @Autowired
    public SlackBotService(final SlackBotWebClient slackBotWebClient) {
        this.slackBotWebClient = slackBotWebClient;
    }

    public void wishBookNotification(final SlackBotBookInfoDto bookInfoDto) {
        slackBotWebClient.wishBookNotification(bookInfoDto).subscribe();
        log.info("Send SlackBot Request - Wish Book Notification");
    }

    public void enrolledNotification(final SlackBotBookInfoDto bookInfoDto) {
        slackBotWebClient.enrolledNotification(bookInfoDto).subscribe();
        log.info("Send SlackBot Request - Enrolled Notification Response");
    }
}
