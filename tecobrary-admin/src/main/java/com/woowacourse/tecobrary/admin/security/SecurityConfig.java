package com.woowacourse.tecobrary.admin.security;

import com.woowacourse.tecobrary.domain.admin.entity.AdminRole;
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

    private static final String DEVELOP_ORIGIN = "http://localhost:3000";

    private final Environment environment;
    private final UserSecurityService userSecurityService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/login", "/oauth2/authorization/**", "/static/**").permitAll()

                .antMatchers("/api/librarybook/**").hasAuthority(AdminRole.ROLE_ADMIN.name())

                .anyRequest().authenticated()
                .and().logout().logoutSuccessUrl("/").invalidateHttpSession(true)
                .and().oauth2Login().userInfoEndpoint().userService(userSecurityService);

        if (environment.acceptsProfiles(Profiles.of("local"))) {
            http.oauth2Login()
                    .defaultSuccessUrl(DEVELOP_ORIGIN)
                    .loginPage(DEVELOP_ORIGIN);
        }
    }
}
