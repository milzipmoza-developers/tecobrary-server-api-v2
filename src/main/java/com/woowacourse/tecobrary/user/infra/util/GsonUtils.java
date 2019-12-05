package com.woowacourse.tecobrary.user.infra.util;

import com.google.gson.Gson;
import com.woowacourse.tecobrary.user.ui.vo.GithubEmailVo;
import com.woowacourse.tecobrary.user.ui.vo.GithubUserInfoVo;

import java.util.Arrays;
import java.util.List;

public class GsonUtils {
    private static Gson GSON;

    static {
         GSON = new Gson();
    }

    public static GithubUserInfoVo parseUserInfo(String jsonString) {
        return GSON.fromJson(jsonString, GithubUserInfoVo.class);
    }

    public static List<GithubEmailVo> parseUserEmail(String jsonString) {
        GithubEmailVo[] array = GSON.fromJson(jsonString, GithubEmailVo[].class);
        return Arrays.asList(array);
    }
}
