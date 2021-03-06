package com.cloud.user.controller;

import com.cloud.common.resp.ResResult;
import com.cloud.user.query.UserQuery;
import com.cloud.user.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * UserController
 *
 * @author lgn
 * @date 2021-11-29 17:10
 */

@RestController
@RequestMapping("/pro/api/user")
public class UserController {

    @Resource
    private UserService userService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/save")
    public ResResult save(@RequestBody UserQuery query) {

        int res = userService.save(query);

        UUID uuid = UUID.randomUUID();
        query.setProServerPort(serverPort);
        query.setAccount(uuid.toString());

        return new ResResult(res == 1, 200, "操作成功", query);
    }

}
