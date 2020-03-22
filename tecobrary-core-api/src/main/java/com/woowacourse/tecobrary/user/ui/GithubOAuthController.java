/*
 * @(#) GithubOAuthController.java
 *
 * v 1.0.0
 *
 * 2019.10.10
 *
 * Copyright (c) 2019 woowacourse, thedevluffy
 * All rights reserved
 */

package com.woowacourse.tecobrary.user.ui;

import com.woowacourse.tecobrary.web.github.utils.GithubUserApiUrlBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/com/woowacourse/tecobrary/github")
public class GithubOAuthController {

    private final GithubUserApiUrlBuilder githubUserApiUrlBuilder;

    @Autowired
    public GithubOAuthController(final GithubUserApiUrlBuilder githubUserApiUrlBuilder) {
        this.githubUserApiUrlBuilder = githubUserApiUrlBuilder;
    }

    @GetMapping("/user/oauth")
    public RedirectView githubConfirmAuthentication() {
        return new RedirectView(githubUserApiUrlBuilder.oauth());
    }
}
