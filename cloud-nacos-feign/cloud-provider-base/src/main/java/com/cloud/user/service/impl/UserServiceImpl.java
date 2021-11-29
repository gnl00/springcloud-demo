package com.cloud.user.service.impl;

import com.cloud.api.user.query.UserQuery;
import com.cloud.user.mapper.UserMapper;
import com.cloud.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * UserServiceImpl
 *
 * @author lgn
 * @date 2021-11-29 17:08
 */

@Slf4j
@Service
public class UserServiceImpl implements UserService {

//    @Resource
//    private UserMapper userMapper;

    @Override
    public int save(UserQuery query) {

        double flag = 0.5;

        if (Math.random() > flag) {

            log.info("===> save user success");

            return 1;
        }

        log.info("===> save user fail");
        return -1;
    }
}
