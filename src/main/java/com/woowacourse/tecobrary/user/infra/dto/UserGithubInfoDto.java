package com.woowacourse.tecobrary.user.infra.dto;

import com.woowacourse.tecobrary.common.model.Email;
import com.woowacourse.tecobrary.common.model.HttpsUrl;
import com.woowacourse.tecobrary.user.command.domain.UserName;
import lombok.*;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public class UserGithubInfoDto {

    private String githubId;
    private UserName name;
    private Email email;
    private HttpsUrl avatarUrl;

    @Builder
    private UserGithubInfoDto(final String id, final String name, final String email, final String avatarUrl) {
        this.githubId = id;
        this.name = new UserName(name);
        this.email = new Email(email);
        this.avatarUrl = new HttpsUrl(avatarUrl);
    }
}
