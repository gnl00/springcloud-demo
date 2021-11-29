package com.cloud.controller;

import com.cloud.api.common.ResResult;
import com.cloud.api.user.domain.req.UserQuery;
import com.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * UserController
 *
 * @author lgn
 * @date 2021-11-29 15:18
 */

@RestController
@RequestMapping("/pro/api/user")
public class UserController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private UserService userService;

    @PostMapping("/save")
    public ResResult save(@RequestBody UserQuery query) {

        int res = userService.save(query);

        UUID uuid = UUID.randomUUID();
        query.setAccount(uuid.toString());
        query.setProServerPort(serverPort);

        return new ResResult(res == 1, 200, "操作成功", query);
    }

}
