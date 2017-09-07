package com.stalary.codeGroup.controller;

import com.stalary.codeGroup.entity.Admin;
import com.stalary.codeGroup.entity.User;
import com.stalary.codeGroup.service.AdminService;
import com.stalary.codeGroup.service.RankService;
import com.stalary.codeGroup.service.UserService;
import com.stalary.codeGroup.util.MD5Utils;
import com.stalary.codeGroup.util.WebUtils;
import com.stalary.codeGroup.viewmodel.ApiResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    private RankService rankService;

    @ApiOperation("用户修改密码，需要传入三个参数 1 token，2 原密码 3 新密码")
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

    @ApiOperation("用户忘记密码时，需要传入三个参数 1 用户账号(学号) 2 姓名 3 密码")
    @RequestMapping(value = "/userForgetPassword",method = RequestMethod.POST)
    public ApiResult userForgetPassword(String studentNo, String name, String password) {
        User user = userService.findByStudentNo(studentNo);
        if(null == user) {
            return ApiResult.error("学号输入错误");
        }
        if(!user.getName().equals(name)) {
            return ApiResult.error("姓名输入错误");
        }
        return userService.alterPassword(user, password);
    }

    @ApiOperation("管理员修改密码 需要传入 三个参数 1 token 2 原密码 3 新密码")
    @RequestMapping(value = "/adminAlterPassword",method = RequestMethod.POST)
    public ApiResult adminAlterPassword(String oldPassword, String newPassword) {
        Integer keyId = WebUtils.getLoginUserId();
        Admin admin = adminService.findOne(keyId);
        if(null == admin) {
            return ApiResult.error("管理员：" + keyId + "不存在");
        }
        if(!admin.getPassword().equals(MD5Utils.MD5(oldPassword))) {
            return ApiResult.error("管理员：" + keyId + "原密码错误");
        }
        return adminService.alterPassword(admin, newPassword);
    }

    @ApiOperation("管理员忘记密码时，需要传入三个参数 1 管理员账号(学号)，2 姓名 3 密码")
    @RequestMapping(value = "/adminForgetPassword",method = RequestMethod.POST)
    public ApiResult adminForgetPassword(String studentNo, String name, String password) {
        Admin admin = adminService.findByStudentNo(studentNo);
        if(null == admin) {
            return ApiResult.error("管理员：" + studentNo + "不存在");
        }
        if(!admin.getName().equals(name)) {
            return ApiResult.error("管理员：" + studentNo + "姓名输入错误");
        }
        return adminService.alterPassword(admin,password);
    }

    @ApiOperation(value = "修改用户积分时调用，需要传入用户的学号，修改数量，修改详情（可选），修改类型 1 签到 2 参加活动 3 比赛 4 违规--（前台）--（后台）")
    @RequestMapping(value = "/alterRank",method = RequestMethod.POST)
    public ApiResult alterRank(String studentNo, String alterNumber, String alterDetail, Integer type) {
        User user = userService.findByStudentNo(studentNo);
        if(null == user) {
            return ApiResult.error("用户" + studentNo + "不存在");
        }
        Integer alterNumberFinal = Integer.parseInt(alterNumber);
        return rankService.alterRank(user.getKeyId(), alterNumberFinal, alterDetail, type);
    }
}
