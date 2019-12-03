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
import com.woowacourse.tecobrary.user.ui.dto.UserNameDto;
import com.woowacourse.tecobrary.user.ui.dto.UserAuthDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(model);
    }

    @GetMapping("/users")
    public ResponseEntity findUsers(@RequestParam final int page, @RequestParam final int number) {
        return ResponseEntity.ok(userService.findUsersOnPage(page, number));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity findUserById(@PathVariable final int id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PatchMapping("/users")
    public ResponseEntity updateUserName(@RequestBody final UserNameDto userNameDto) {
        long userId = userService.updateUserName(userNameDto);
        return ResponseEntity.ok(userService.findUserById(userId));
    }

    @PostMapping("/users")
    public ResponseEntity updateUserAuth(@RequestBody final UserAuthDto userAuthDto) {
        Long userId = userService.updateUserAuth(userAuthDto);
        return ResponseEntity.ok(userService.findUserById(userId));
    }
}
