package com.cloud.service;

import com.cloud.api.user.domain.dto.UserDto;
import com.cloud.api.user.domain.req.UserQuery;

import java.util.List;

/**
 * UserService
 *
 * @author lgn
 * @date 2021-11-29 14:12
 */

public interface UserService {

    /**
     * query user
     * @param query
     * @return com.cloud.domain.resp.UserVo
     * @auto lgn
     */
    UserDto query(UserQuery query);

    /**
     * query users
     * @param query
     * @return List<UserVo>
     * @auto lgn
     */
    List<UserDto> queryList(UserQuery query);

    /**
     * save user
     * @param query
     * @return int
     * @auto lgn
     */
    int save(UserQuery query);

    /**
     * update user
     * @param query
     * @return int
     * @auto lgn
     */
    int update(UserQuery query);

    /**
     * delete user
     * @param query
     * @return java.lang.Boolean
     * @auto lgn
     */
    Boolean delete(UserQuery query);

}
