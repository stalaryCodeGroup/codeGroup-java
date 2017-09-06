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
import com.stalary.codeGroup.util.MD5Utils;
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
 * @Description:
 * @Date Created in 2017/9/3
 */
@RestController
@RequestMapping(value = "/edit")
public class EditController {

    @Resource
    private AdminService adminService;
    @Resource
    private LogService logService;
    @Resource
    private UserService userService;
    @Resource
    private RankService rankService;

    @ApiOperation(value = "添加管理员，职位为1的会长才可以调用，需要传入姓名，账号，密码，职务，年级 1 会长 2 副会长 3 部门部长")
    @RequestMapping(value = "/addAdmin",method = RequestMethod.POST)
    public ApiResult addAdmin(String result) {
        Integer keyId = WebUtils.getLoginUserId();//获得管理员的keyId
        Admin tokenAdmin = adminService.findOne(keyId);
        if(tokenAdmin.getPosition() != 1) {
            return ApiResult.error("管理员权限不够");
        }
        JSONObject jsonObject = JSON.parseObject(result);//接收前台的json串
        Admin admin = adminService.findByStudentNo((String) jsonObject.get("studentNo"));
        if(null != admin) {
            return ApiResult.error("管理员：" + jsonObject.get("studentNo") + "已存在");
        }
        Admin newAdmin = new Admin();
        newAdmin.setName((String) jsonObject.get("name"));//姓名
        newAdmin.setPhone((String) jsonObject.get("phone"));//账号
        newAdmin.setPassword(MD5Utils.MD5((String) jsonObject.get("password")));//MD5加密的密码
        newAdmin.setPosition(Integer.parseInt((String) jsonObject.get("position")));//职务 1 会长 2 副会长 3 部门部长
        newAdmin.setStudentNo((String) jsonObject.get("studentNo"));//账号(学号)
        newAdmin.setYear(Integer.parseInt((String) jsonObject.get("year")));//年级
        try {
            adminService.save(newAdmin);
            logService.create("管理员" + jsonObject.get("name") + "添加成功");
            return ApiResult.ok("管理员" + jsonObject.get("studentNo") + "添加成功");
        } catch (Exception e) {
            logService.create("管理员" + jsonObject.get("name") + "添加失败");
            return ApiResult.error("管理员" + jsonObject.get("name") + "添加失败");
        }
    }

    @ApiOperation(value = "删除管理员，职位为1的会长才可以调用，需要传入要删除管理员的keyId")
    @RequestMapping(value = "/deleteAdmin",method = RequestMethod.POST)
    public ApiResult deleteAdmin(String studentNo) {
        Integer keyId = WebUtils.getLoginUserId();
        Admin admin = adminService.findByStudentNo(studentNo);
        if(null == admin) {
            return ApiResult.error("管理员不存在");
        }
        Admin tokenAdmin = adminService.findOne(keyId);
        if(tokenAdmin.getPosition() != 1) {
            return ApiResult.error("管理员权限不足");
        }
        adminService.deleteById(admin.getKeyId());
        return ApiResult.ok("管理员删除成功");
    }

    @ApiOperation(value = "删除用户，需要传入用户的学号")
    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    public ApiResult deleteUser(String studentNo) {
        User user = userService.findByStudentNo(studentNo);
        if(null == user) {
            return ApiResult.error("用户不存在");
        }
        List<Rank> rankList = rankService.findByUserKeyId(user.getKeyId());
        if(null == rankList || 0 == rankList.size()) {
            userService.deleteById(user.getKeyId());
            return ApiResult.ok("用户删除成功");
        }
        int size = rankList.size();
        for(int i = 0; i < size; i++) {
            Rank rank = rankList.get(i);
            rankService.deleteById(rank.getKeyId());
            logService.create("用户：" + user.getKeyId() + "积分信息删除成功");
        }
        userService.deleteById(user.getKeyId());
        return ApiResult.ok("用户删除成功");
    }
}
