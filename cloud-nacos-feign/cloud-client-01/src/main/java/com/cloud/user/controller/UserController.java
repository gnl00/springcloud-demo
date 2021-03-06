package com.cloud.user.controller;

import com.cloud.common.resp.ResResult;
import com.cloud.user.query.UserQuery;
import com.cloud.user.client.UserClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author lgn
 * @date 2021-11-29 15:59
 */

@RestController
@RequestMapping("/cli/user")
public class UserController {

    @Resource
    private UserClient userClient;

    @PostMapping("/save")
    public ResResult save(@RequestBody UserQuery query) {

        ResResult result = userClient.save(query);

        return result;
    }

}
