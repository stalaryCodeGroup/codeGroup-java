package com.stalary.codeGroup.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.stalary.codeGroup.entity.Admin;
import com.stalary.codeGroup.entity.User;
import com.stalary.codeGroup.service.AdminService;
import com.stalary.codeGroup.service.LogService;
import com.stalary.codeGroup.service.UserService;
import com.stalary.codeGroup.util.DigestUtil;
import com.stalary.codeGroup.util.MD5Utils;
import com.stalary.codeGroup.util.WebUtils;
import com.stalary.codeGroup.viewmodel.ApiError;
import com.stalary.codeGroup.viewmodel.ApiResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:Stalary
 * @Description:控制用户和管理员登陆
 * @Date Created in 2017/8/24
 */
@RestController
@RequestMapping(value = "login")
public class LoginController {

    @Resource
    private UserService userService;
    @Resource
    private LogService logService;
    @Resource
    private AdminService adminService;

    @ApiOperation(value = "用户登陆时调用")
    @RequestMapping(value = "/userLogin",method = RequestMethod.POST)
    public ApiResult userLogin(String studentNo, String password) {
        User user = userService.findByStudentNo(studentNo);//通过学号查找用户
        if(null == user) {
            return ApiError.accountNotFound();
        }
        if(!user.getPassword().equals(MD5Utils.MD5(password))) {
            return ApiError.errorPassword();
        }
        user.setLoginTime(new Date());//存储用户的登陆时间
        userService.save(user);
        Map<String, Object> resultMap = new HashMap<>();
        String token = DigestUtil.Encrypt(user.getKeyId() + ":" + studentNo);
        resultMap.put("token", token);
        return ApiResult.ok(resultMap);
    }

    @ApiOperation(value = "用户注册时调用，需要传入表单数据（json格式）->账号，姓名，密码，学号，性别，向前台返回token")
    @RequestMapping(value = "/userRegister", method = RequestMethod.POST)
    public ApiResult userRegister(String result) {
        JSONObject jsonObject = JSON.parseObject(result);//接收前台的json串
        User user = new User();
        user.setAccount((String) jsonObject.get("account"));//账号
        user.setName((String) jsonObject.get("name"));//姓名
        user.setLoginTime(new Date());//登陆时间
        user.setRegisterTime(new Date());//注册时间
        user.setPassword(MD5Utils.MD5((String) jsonObject.get("password")));//MD5加密的密码
        user.setRank(1000);//默认积分为1000
        user.setStudentNo((String) jsonObject.get("studentNo"));//学号
        user.setSex((String) jsonObject.get("sex"));//性别
        Map<String, Object> resultMap = new HashMap<>();
        try {
            userService.save(user);
            logService.create("用户：" + user.getKeyId() + "注册成功");
            String token = DigestUtil.Encrypt(user.getKeyId() + ":" + user.getName());
            resultMap.put("token", token);
        } catch (Exception e) {
            logService.create("用户注册失败");
            resultMap.put("token", null);
        }

        return ApiResult.ok(resultMap);
    }

    @ApiOperation(value = "管理员登录")
    @RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
    public ApiResult adminLogin(String studentNo, String password) {

        Admin admin = adminService.findByStudentNo(studentNo);

        if (null == admin) {
            return ApiError.accountNotFound();
        }
        if (!admin.getPassword().equals(MD5Utils.MD5(password))) {
            return ApiError.errorPassword();
        }
        Map<String, Object> resultMap = new HashMap<>();
        String token = DigestUtil.Encrypt(admin.getKeyId() + ":" + studentNo);
        resultMap.put("token", token);
        return ApiResult.ok(resultMap);
    }

    @ApiOperation(value = "添加管理员，职位为1的会长才可以调用，需要传入姓名，账号，密码，职务 1 会长 2 副会长 3 部门部长")
    @RequestMapping(value = "/addAdmin",method = RequestMethod.POST)
    public ApiResult addAdmin(String name, String account, String password, Integer position, String studentNo) {
        Admin admin = adminService.findByAccount(account);
        if(null != admin) {
            return ApiResult.error("管理员：" + account + "已存在");
        }
        Admin newAdmin = new Admin();
        newAdmin.setName(name);//姓名
        newAdmin.setAccount(account);//账号
        newAdmin.setPassword(MD5Utils.MD5(password));//MD5加密的密码
        newAdmin.setPosition(position);//职务 1 会长 2 副会长 3 部门部长
        newAdmin.setStudentNo(studentNo);//学号
        try {
            adminService.save(newAdmin);
            logService.create("管理员" + name + "添加成功");
            return ApiResult.ok("管理员" + name + "添加成功");
        } catch (Exception e) {
            logService.create("管理员" + name + "添加失败");
            return ApiResult.error("管理员" + name + "添加失败");
        }
    }

    @ApiOperation(value = "删除管理员，职位为1的会长才可以调用，需要传入要删除管理员的keyId")
    @RequestMapping(value = "/deleteAdmin",method = RequestMethod.POST)
    public ApiResult deleteAdmin(Integer keyId) {
        Admin admin = adminService.findOne(keyId);
        if(null == admin) {
            return ApiResult.error("管理员不存在");
        }
        adminService.deleteById(keyId);
        return ApiResult.ok("管理员删除成功");
    }

    @ApiOperation(value = "删除用户，需要传入用户的keyId")
    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    public ApiResult deleteUser(Integer keyId) {
        User user = userService.findOne(keyId);
        if(null == user) {
            return ApiResult.error("用户不存在");
        }
        userService.deleteById(keyId);
        return ApiResult.ok("用户删除成功");
    }

}
