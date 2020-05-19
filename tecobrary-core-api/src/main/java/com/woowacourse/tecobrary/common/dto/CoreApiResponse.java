package com.woowacourse.tecobrary.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CoreApiResponse<T> {

    private final String code;
    private final String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime serverDateTime;
    private final T data;

    private CoreApiResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.serverDateTime = LocalDateTime.now();
        this.data = data;
    }

    public static <T> CoreApiResponse<T> success(T data) {
        return new CoreApiResponse<>("200", "", data);
    }

    public static <T> CoreApiResponse<T> success(String message) {
        return new CoreApiResponse<>("200", message, null);
    }

    public static <T> CoreApiResponse<T> fail(String code, String message) {
        return new CoreApiResponse<>(code, message, null);
    }
}
