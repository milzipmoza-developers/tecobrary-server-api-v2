package com.woowacourse.tecobrary.admin.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final Environment environment;
    private final UserSecurityService userSecurityService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/login", "/oauth2/authorization/**", "/static/**")
                .permitAll()

                .antMatchers(HttpMethod.GET, "/api/librarybooks/**")
                .hasAnyRole()

                .antMatchers(HttpMethod.POST, "/api/librarybooks/**")
                .hasAnyRole()

                .anyRequest().authenticated()
                .and().oauth2Login()
                .userInfoEndpoint()
                .userService(userSecurityService);

        if (environment.acceptsProfiles(Profiles.of("local"))) {
            http.oauth2Login()
                    .defaultSuccessUrl("http://localhost:3000")
                    .loginPage("http://localhost:3000");
        }
    }
}
