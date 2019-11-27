package com.woowacourse.tecobrary.common.model;

import com.woowacourse.tecobrary.common.exception.MalformedEmailException;
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
public class Email {

    private static final String EMAIL_REGEX = "^(.+)@(.+)$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    @Column(name = "email",
            nullable = false,
            length = 100)
    private String email;

    public Email(String email) {
        checkEmailForm(email);
        this.email = email;
    }

    private void checkEmailForm(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        if (!matcher.matches()) {
            throw new MalformedEmailException();
        }
    }
}
