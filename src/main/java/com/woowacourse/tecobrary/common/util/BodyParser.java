package com.woowacourse.tecobrary.common.util;

import java.util.HashMap;
import java.util.Map;

public class BodyParser {

    public static Map<String, String> parse(String body) {
        String[] responses = body.split("&");
        Map<String, String> response = new HashMap<>();
        for (String res : responses) {
            String[] token = res.split("=");
            response.put(token[0], token[1]);
        }
        return response;
    }
}
