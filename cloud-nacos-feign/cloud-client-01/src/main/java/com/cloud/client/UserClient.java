package com.cloud.client;

import com.cloud.api.common.ResResult;
import com.cloud.api.user.domain.req.UserQuery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * UserFeignClient
 *
 * @author lgn
 * @date 2021-11-29 15:03
 */

@FeignClient("cloud-provider")
@RequestMapping("/pro/api/user")
@ResponseBody
public interface UserClient {

    /**
     * save user
     * @param query
     * @return java.lang.Object
     * @author lgn
     */
    @PostMapping("/save")
    ResResult save(@RequestBody UserQuery query);

}
