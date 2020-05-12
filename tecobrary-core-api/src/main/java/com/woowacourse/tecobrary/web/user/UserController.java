package com.woowacourse.tecobrary.web.user;

import com.woowacourse.tecobrary.common.dto.CoreApiResponse;
import com.woowacourse.tecobrary.web.user.dto.UserNameDto;
import com.woowacourse.tecobrary.web.user.service.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api/v2")
public class UserController {

    private final UserFacade userFacade;

    @GetMapping("/users/{id}")
    public CoreApiResponse findUserById(@PathVariable final long id) {
        return CoreApiResponse.success(userFacade.findUserById(id));
    }

    @PatchMapping("/users")
    public CoreApiResponse updateUserName(@RequestBody final UserNameDto userNameDto) {
        long userId = userFacade.updateUserName(userNameDto);
        return CoreApiResponse.success(userFacade.findUserById(userId));
    }
}
