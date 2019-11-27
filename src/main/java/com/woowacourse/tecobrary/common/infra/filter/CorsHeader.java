package com.woowacourse.tecobrary.common.infra.filter;

public interface CorsHeader {
    String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";
    String ACCESS_CONTROL_MAX_AGE = "Access-Control-Max-Age";
    String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
    String AUTHORIZATION_CONTENT_TYPE = "authorization, content-type";
    String ALLOW_METHODS = "GET, POST, PUT, DELETE, OPTIONS";
    String MAX_AGE = "3600";
    String ALLOW_ORIGIN = "*";
    String OPTIONS = "OPTIONS";
}
