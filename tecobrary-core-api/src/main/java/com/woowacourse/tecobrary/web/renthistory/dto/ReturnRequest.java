package com.woowacourse.tecobrary.web.renthistory.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class ReturnRequest {

    private Long serial;
    private Long userId;

    public ReturnRequest(Long serial, Long userId) {
        this.serial = serial;
        this.userId = userId;
    }
}
