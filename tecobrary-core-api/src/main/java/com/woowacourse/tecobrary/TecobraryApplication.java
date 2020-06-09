package com.woowacourse.tecobrary;

import com.woowacourse.tecobrary.security.config.JwtAuthConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.woowacourse.tecobrary.*")
@EnableJpaRepositories("com.woowacourse.tecobrary.*")
@EnableConfigurationProperties(JwtAuthConfig.class)
public class TecobraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(TecobraryApplication.class, args);
    }

}

