package com.cloud.user.controller;

import com.cloud.common.resp.ResResult;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户权限Controller
 *
 * @author lgn
 * @date 2021-11-30 11:38
 */

@RestController
@RequestMapping("/auth/user")
public class UserAuthController {

    /**
     * 检查用户是否有访问此路径的权限
     * @param userId
     * @param path
     * @return ResResult<Boolean>
     */
    @GetMapping("/path")
    public ResResult<Boolean> checkByPath(String userId, String path) {

        if (!StringUtils.isEmpty(path)) {
            return new ResResult(true, 200, "", true);
        }

        return new ResResult(true, 200, "", false);
    }

    /**
     * 检查用户是否有使用此功能的权限
     * @param userId
     * @param func 页面::功能
     * @return ResResult<Boolean>
     */
    @GetMapping("/fun")
    public ResResult<Boolean> checkByFunction(String userId, String func) {
        return null;
    }

}
