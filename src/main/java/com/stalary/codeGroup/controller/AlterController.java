package com.stalary.codeGroup.controller;

import com.stalary.codeGroup.entity.User;
import com.stalary.codeGroup.service.AdminService;
import com.stalary.codeGroup.service.LogService;
import com.stalary.codeGroup.service.UserService;
import com.stalary.codeGroup.util.MD5Utils;
import com.stalary.codeGroup.util.WebUtils;
import com.stalary.codeGroup.viewmodel.ApiResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;

/**
 * @Author:Stalary
 * @Description:修改信息时调用
 * @Date Created in 2017/8/24
 */
@RestController
@RequestMapping(value = "alter")
public class AlterController {

    @Resource
    private AdminService adminService;
    @Resource
    private UserService userService;
    @Resource
    private LogService logService;

    @ApiOperation("用户修改密码，需要传入两个参数 1 token，2 原密码 3 新密码")
    @RequestMapping(value = "/userAlterPassword",method = RequestMethod.POST)
    public ApiResult userAlterPassword(String oldPassword, String newPassword) {
        Integer keyId = WebUtils.getLoginUserId();
        User user = userService.findOne(keyId);
        if(null == user) {
            return ApiResult.error("用户：" + keyId + "不存在");
        }
        if(!user.getPassword().equals(MD5Utils.MD5(oldPassword))) {
            return ApiResult.error("用户：" + keyId + "原密码输入错误");
        }
        return userService.alterPassword(user,newPassword);
    }

    @ApiOperation("用户忘记密码时，需要传入三个参数 1 token，2 学号 3 密码")
    @RequestMapping(value = "/userForgetPassword",method = RequestMethod.POST)
    public ApiResult userForgetPassword(String studentNo, String password) {
        Integer keyId = WebUtils.getLoginUserId();
        User user = userService.findByStudentNo(studentNo);
        if(null == user) {
            return ApiResult.error("用户：" + keyId + "学号输入错误");
        }
        return userService.alterPassword(user,password);
    }
}
