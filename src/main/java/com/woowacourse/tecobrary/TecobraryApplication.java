package com.woowacourse.tecobrary;

import com.woowacourse.tecobrary.utility.YamlPropertySourceFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(
        value = {
                "classpath:github.yml",
                "classpath:jwtauth.yml",
                "classpath:naverapi.yml",
        },
        ignoreResourceNotFound = true,
        factory = YamlPropertySourceFactory.class
)

public class TecobraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(TecobraryApplication.class, args);
    }

}
