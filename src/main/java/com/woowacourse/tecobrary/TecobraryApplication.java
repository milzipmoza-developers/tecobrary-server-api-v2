package com.woowacourse.tecobrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class TecobraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(TecobraryApplication.class, args);
    }
}
