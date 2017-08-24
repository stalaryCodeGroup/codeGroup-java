package com.stalary.codeGroup.controller;

import com.stalary.codeGroup.viewmodel.ApiResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:Stalary
 * @Description:控制用户和管理员登陆
 * @Date Created in 2017/8/24
 */
@RestController
@RequestMapping(value = "login")
public class LoginController {

    @ApiOperation(value = "测试接口")
    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public ApiResult test() {
        return ApiResult.ok("测试成功");
    }
}
