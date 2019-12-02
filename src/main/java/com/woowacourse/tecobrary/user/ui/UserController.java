/*
 * @(#) UserController.java
 *
 * v 1.0.0
 *
 * 2019.11.29
 *
 * Copyright (c) 2019 woowacourse, thedevluffy, gch01410, LeeYounghyeon
 * All rights reserved
 */

package com.woowacourse.tecobrary.user.ui;

import com.woowacourse.tecobrary.user.command.application.UserService;
import com.woowacourse.tecobrary.user.ui.dto.UserInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/all")
    public ResponseEntity countOfUser(Model model) {
        model.addAttribute("total", userService.countOfUser());
        return ResponseEntity.ok().body(model);
    }

    @GetMapping("/users")
    public ResponseEntity findUsers(@RequestParam int page, @RequestParam int number, Model model) {
        List<UserInfoDto> users = userService.findUsersOnPage(page, number);
        model.addAttribute("users", users);
        return ResponseEntity.ok().body(model);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity findUserById(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return ResponseEntity.ok().body(model);
    }
}
