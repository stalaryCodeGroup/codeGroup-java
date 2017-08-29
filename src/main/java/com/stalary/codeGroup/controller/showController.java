package com.stalary.codeGroup.controller;

import com.stalary.codeGroup.entity.Admin;
import com.stalary.codeGroup.entity.User;
import com.stalary.codeGroup.service.AdminService;
import com.stalary.codeGroup.service.UserService;
import com.stalary.codeGroup.util.WebUtils;
import com.stalary.codeGroup.viewmodel.ApiResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:Stalary
 * @Description:展示信息时调用
 * @Date Created in 2017/8/24
 */
@RestController
@RequestMapping(value = "show")
public class showController {

    @Resource
    private UserService userService;
    @Resource
    private AdminService adminService;

    @ApiOperation(value = "展示用户信息时调用，需要传入排序的类型 1 按照rank积分排序 2 按照注册日期排序")
    @RequestMapping(value = "/showUser",method = RequestMethod.POST)
    public ApiResult showUser(Integer type) {
        if(1 == type) {
            List<User> userList = userService.sortByRank();//通过rank排序
            if(null == userList || 0 == userList.size()) {
                return ApiResult.ok("无用户");
            }
            return ApiResult.ok(userList);
        } else if(2 == type) {
            List<User> userList = userService.sortByRegisterTime();//通过注册日期排序
            if(null == userList || 0 == userList.size()) {
                return ApiResult.ok("无用户");
            }
            return ApiResult.ok(userList);
        } else {
            return ApiResult.error("传入类型错误");
        }
    }

    @ApiOperation(value = "展示管理员列表")
    @RequestMapping(value = "/showAdmin",method = RequestMethod.POST)
    public ApiResult showAdmin() {
        List<Admin> adminList = adminService.sortByPosition();
        if(null == adminList || 0 == adminList.size()) {
            return ApiResult.ok("无管理员");
        }
        return ApiResult.ok(adminList);
    }

    @ApiOperation(value = "展示用户信息")
    @RequestMapping(value = "/showOneUser",method = RequestMethod.POST)
    public ApiResult showOneUser() {
        Integer keyId = WebUtils.getLoginUserId();
        User user = userService.findOne(keyId);
        if(null == user) {
            return ApiResult.error("用户：" + keyId + "不存在");
        }
        return ApiResult.ok(user);
    }
}
