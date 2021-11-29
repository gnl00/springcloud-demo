package com.boot.cloud.user.service;

import com.boot.cloud.common.resp.PageResult;
import com.boot.cloud.user.domain.dto.UserDto;
import com.boot.cloud.user.domain.entities.User;
import com.boot.cloud.user.domain.query.UserQuery;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserServiceImpl
 *
 * @author lgn
 */

@DubboService(version = "1.0.1")
@Service
public class UserServiceImpl implements UserService {

    @Value("${server.port}")
    private String serverPort;

    @Override
    public Integer save(UserQuery query) {

        System.out.println("===> UserServiceImpl#save here iam " + serverPort);

        double random = Math.random();

        if (random < 0.5) {
            System.out.println("===> UserServiceImpl#save save user fail");
            return 0;
        } else {
            System.out.println("===> UserServiceImpl#save save user success");
            return 1;
        }
    }

    @Override
    public Integer update(UserQuery query) {
        return null;
    }

    @Override
    public Boolean delete(UserQuery query) {
        return null;
    }

    @Override
    public UserDto queryOne(UserQuery query) {
        return null;
    }

    @Override
    public List<UserDto> queryList(UserQuery query) {
        return null;
    }

    @Override
    public PageResult<User> queryByPage(UserQuery query) {
        return null;
    }
}
