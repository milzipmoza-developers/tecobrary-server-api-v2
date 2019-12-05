package com.woowacourse.tecobrary.common.model;

import com.woowacourse.tecobrary.common.exception.MalformedHttpsUrlException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class HttpsUrl {

    private static final String URL_REGEX = "^(https?):\\/\\/([^:\\/\\s]+)(:([^\\/]*))?((\\/[^\\s/\\/]+)*)?\\/?([^#\\s\\?]*)(\\?([^#\\s]*))?(#(\\w*))?$";
    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

    @Column(name = "url",
            nullable = false,
            length = 255)
    private String url;

    public HttpsUrl(String url) {
        checkUrlForm(url);
        this.url = url;
    }

    private void checkUrlForm(String url) {
        Matcher matcher = URL_PATTERN.matcher(url);
        if (!matcher.matches()) {
            throw new MalformedHttpsUrlException();
        }
    }
}
