package com.cloud.user.client;

import com.cloud.api.common.resp.ResResult;
import com.cloud.api.user.query.UserQuery;
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

@FeignClient(value = "cloud-provider") // 大小写要和注册在 nacos 上的服务名一致
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
