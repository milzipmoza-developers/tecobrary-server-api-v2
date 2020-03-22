package com.woowacourse.tecobrary.web.github.utils;

import com.google.gson.Gson;
import com.woowacourse.tecobrary.web.github.dto.GithubEmailDto;
import com.woowacourse.tecobrary.web.github.dto.GithubUserInfoDto;

import java.util.Arrays;
import java.util.List;

public class GsonUtils {
    private static Gson GSON;

    static {
         GSON = new Gson();
    }

    public static GithubUserInfoDto parseUserInfo(final String jsonString) {
        return GSON.fromJson(jsonString, GithubUserInfoDto.class);
    }

    public static List<GithubEmailDto> parseUserEmail(final String jsonString) {
        GithubEmailDto[] array = GSON.fromJson(jsonString, GithubEmailDto[].class);
        return Arrays.asList(array);
    }
}
