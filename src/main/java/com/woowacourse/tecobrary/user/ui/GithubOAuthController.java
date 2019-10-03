package com.woowacourse.tecobrary.user.ui;

import com.woowacourse.tecobrary.user.infra.util.GithubUserApiUtils;
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
@RequestMapping("/github")
public class GithubOAuthController {

    private final GithubUserApiUtils githubUserApiUtils;

    @Autowired
    public GithubOAuthController(GithubUserApiUtils githubUserApiUtils) {
        this.githubUserApiUtils = githubUserApiUtils;
    }

    @GetMapping("/user/oauth")
    public RedirectView githubConfirmAuthentication() {
        return new RedirectView(githubUserApiUtils.buildOAuthUrl());
    }
}
