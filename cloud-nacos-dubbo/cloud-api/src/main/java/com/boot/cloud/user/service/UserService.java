package com.boot.cloud.user.service;

import com.boot.cloud.common.resp.PageResult;
import com.boot.cloud.user.domain.dto.UserDto;
import com.boot.cloud.user.domain.entities.User;
import com.boot.cloud.user.domain.query.UserQuery;

import java.util.List;

/**
 * UserService
 * @author lgn
 */

public interface UserService {

    /**
     * save user
     * @param query
     * @return java.lang.Integer
     */
    Integer save(UserQuery query);

    /**
     * update user
     * @param query
     * @return java.lang.Integer
     */
    Integer update(UserQuery query);

    /**
     * delete user
     * @param query
     * @return java.lang.Boolean
     */
    Boolean delete(UserQuery query);

    /**
     * query one user
     * @param query
     * @return com.boot.cloud.user.domain.dto.UserDto
     */
    UserDto queryOne(UserQuery query);

    /**
     * query users
     * @param query
     * @return java.util.List<com.boot.cloud.user.domain.dto.UserDto>
     */
    List<UserDto> queryList(UserQuery query);

    /**
     * query user by page
     * @param query
     * @return PageResult<User>
     */
    PageResult<User> queryByPage(UserQuery query);

}
