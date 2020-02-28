package com.woowacourse.tecobrary.common.infra.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CorsFilterConfig {

    @SuppressWarnings("unchecked")
    @Bean
    public FilterRegistrationBean getCorsFilterRegistration() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new CorsFilter());
        registrationBean.setOrder(1);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
