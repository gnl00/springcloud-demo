package com.cloud.common.controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MainController
 *
 * @author lgn
 * @date 2021-11-29 17:07
 */

@RefreshScope
@RestController
@RequestMapping("/base")
public class MainController {
}
