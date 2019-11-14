package com.woowacourse.tecobrary.common.infra.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtValidateFilterConfig {

    @SuppressWarnings("unchecked")
    @Bean
    public FilterRegistrationBean getJwtValidateFilterRegistration() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new JwtFilter());
        registrationBean.setOrder(2);
        registrationBean.addUrlPatterns("/token/auth");
        return registrationBean;
    }
}
