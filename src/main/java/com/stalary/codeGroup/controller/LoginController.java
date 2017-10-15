package com.stalary.codeGroup.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.stalary.codeGroup.entity.Admin;
import com.stalary.codeGroup.entity.Rank;
import com.stalary.codeGroup.entity.User;
import com.stalary.codeGroup.service.AdminService;
import com.stalary.codeGroup.service.LogService;
import com.stalary.codeGroup.service.RankService;
import com.stalary.codeGroup.service.UserService;
import com.stalary.codeGroup.util.DigestUtil;
import com.stalary.codeGroup.util.MD5Utils;
import com.stalary.codeGroup.viewmodel.ApiError;
import com.stalary.codeGroup.viewmodel.ApiResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

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
    @Resource
    private RankService rankService;

    @ApiOperation(value = "用户登陆时调用，需要传入两个参数 1 账号(学号) 2 密码")
    @RequestMapping(value = "/userLogin",method = RequestMethod.POST)
    public ApiResult userLogin(String studentNo, String password) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Date date = calendar.getTime();
        User user = userService.findByStudentNo(studentNo);//通过学号查找用户
        if(null == user) {
            return ApiError.accountNotFound();
        }
        if(!user.getPassword().equals(MD5Utils.MD5(password))) {
            return ApiError.errorPassword();
        }
        user.setLoginTime(date);//存储用户的登陆时间
        userService.save(user);
        Map<String, Object> resultMap = new HashMap<>();
        String token = DigestUtil.Encrypt(user.getKeyId() + ":" + studentNo);
        resultMap.put("token", token);
        return ApiResult.ok(resultMap);
    }

    @ApiOperation(value = "用户注册时调用，需要传入表单数据（json格式）->手机号，姓名，密码，学号，性别，家乡，邮箱，年级，专业，qq号向前台返回token")
    @RequestMapping(value = "/userRegister", method = RequestMethod.POST)
    public ApiResult userRegister(String result) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Date date = calendar.getTime();
        JSONObject jsonObject = JSON.parseObject(result);//接收前台的json串
        if(null != userService.findByStudentNo((String) jsonObject.get("studentNo"))) {
            return ApiResult.error("已注册，请勿重复注册！");
        }
        User user = new User();
        user.setRegisterTime(date);//注册时间
        user.setLoginTime(date);//登陆时间
        user.setPhone((String) jsonObject.get("phone"));//手机号
        user.setName((String) jsonObject.get("name"));//姓名
        user.setPassword(MD5Utils.MD5((String) jsonObject.get("password")));//MD5加密的密码
        user.setRank(1000);//默认积分为1000
        user.setStudentNo((String) jsonObject.get("studentNo"));//账号(学号)
        String finalSex = jsonObject.get("sex").equals("male") ? "男" : "女";
        user.setSex(finalSex);//性别
        user.setMajor((String) jsonObject.get("major"));//专业
        user.setMail((String) jsonObject.get("mail"));//邮箱
        user.setQQ((String) jsonObject.get("qq"));//qq号
        user.setYear((String) jsonObject.get("year"));//年级
        user.setRegion((String) jsonObject.get("region"));//家乡
        Map<String, Object> resultMap = new HashMap<>();
        try {
            userService.save(user);
            Rank rank = new Rank();
            rank.setUser_keyId(user.getKeyId());
            rank.setType(5);
            rank.setAlterNumber(1000);
            rank.setAlterDetail("用户注册，初始积分1000");
            rankService.save(rank);
            logService.create("用户：" + user.getKeyId() + "注册成功");
            String token = DigestUtil.Encrypt(user.getKeyId() + ":" + user.getName());
            resultMap.put("token", token);
            return ApiResult.ok(resultMap);
        } catch (Exception e) {
            logService.create("用户注册失败");
            return ApiResult.error("注册失败");
        }
    }

    @ApiOperation(value = "管理员登录,需要传入两个参数 1 账号(学号) 2 密码")
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

}
