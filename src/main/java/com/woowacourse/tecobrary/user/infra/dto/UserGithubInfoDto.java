package com.woowacourse.tecobrary.user.infra.dto;

import com.woowacourse.tecobrary.common.model.Email;
import com.woowacourse.tecobrary.common.model.HttpsUrl;
import com.woowacourse.tecobrary.user.command.domain.UserName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@NoArgsConstructor
@Getter
@ToString
public class UserGithubInfoDto {

    private String githubId;
    private UserName name;
    private Email email;
    private HttpsUrl avatarUrl;

    public UserGithubInfoDto(String id, String name, String email, String avatarUrl) {
        this.githubId = id;
        this.name = new UserName(name);
        this.email = new Email(email);
        this.avatarUrl = new HttpsUrl(avatarUrl);
    }
}
