package com.woowacourse.tecobrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.woowacourse.tecobrary.domain.*")
@EnableJpaRepositories("com.woowacourse.tecobrary.domain.*")
public class TecobraryAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(TecobraryAdminApplication.class, args);
    }

}

