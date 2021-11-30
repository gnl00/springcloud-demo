package com.cloud.user.controller;

import com.cloud.common.resp.ResResult;
import com.cloud.user.query.UserAuthQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * 用户权限Controller
 *
 * @author lgn
 * @date 2021-11-30 11:38
 */

@Slf4j
@RestController
@RequestMapping("/auth/user")
public class UserAuthController {

    /**
     * 检查用户是否有访问此路径的权限
     * @param auth
     * @return ResResult<Boolean>
     */
    @GetMapping("/path")
    public ResResult<Boolean> pathCheck(@RequestBody UserAuthQuery auth) {

        if (Objects.nonNull(auth)) {

            log.info("auth_test query info ===> ${}", auth);

            return new ResResult(true, 200, "操作成功", true);
        }
        return new ResResult(true, 200, "操作成功", false);
    }

    /**
     * 检查用户是否有使用此功能的权限
     * @param auth
     * @return ResResult<Boolean>
     */
    @GetMapping("/fun")
    public ResResult<Boolean> funcCheck(@RequestBody UserAuthQuery auth) {
        return null;
    }

}
