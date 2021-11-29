package com.cloud.user.service.impl;

import com.cloud.api.user.dto.UserDto;
import com.cloud.api.user.query.UserQuery;
import com.cloud.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserServiceImpl
 *
 * @author lgn
 * @date 2021-11-29 14:27
 */

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserDto query(UserQuery query) {
        return null;
    }

    @Override
    public List<UserDto> queryList(UserQuery query) {
        return null;
    }

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

    @Override
    public int update(UserQuery query) {
        return 0;
    }

    @Override
    public Boolean delete(UserQuery query) {
        return null;
    }

}
