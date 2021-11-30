package com.cloud.user.service;

import com.cloud.user.query.UserQuery;

/**
 * UserService
 *
 * @author lgn
 * @date 2021-11-29 17:08
 */


public interface UserService {

    /**
     * save user
     * @param query
     * @return int
     * @author lgn
     * @date 2021/11/29 17:18
     */
    int save(UserQuery query);

}
