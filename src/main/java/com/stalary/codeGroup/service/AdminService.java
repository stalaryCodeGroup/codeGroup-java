package com.stalary.codeGroup.service;

import com.stalary.codeGroup.entity.Admin;
import com.stalary.codeGroup.repo.AdminRepo;
import com.stalary.codeGroup.util.MD5Utils;
import com.stalary.codeGroup.viewmodel.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:Stalary
 * @Description:管理员业务层，处理管理员操作
 * @Date Created in 2017/8/24
 */
@Service
public class AdminService extends BaseService<Admin,AdminRepo>{

    @Autowired
    public AdminService(AdminRepo repo) {
        super(repo);
    }

    public Admin findByPhone(String phone) {
        return repo.findByPhone(phone);//通过账号查找管理员
    }

    public Admin findByStudentNo(String studentNo) {
        return repo.findByStudentNo(studentNo);//通过学号查找管理员
    }

    public List<Admin> sortByPositionAndYear() {
        return repo.sortByPositionAndYear();//通过职务排序
    }

    public ApiResult alterPassword(Admin admin, String password) {
        admin.setPassword(MD5Utils.MD5(password));
        try {
            save(admin);
            return ApiResult.ok("管理员密码修改成功");
        } catch (Exception e) {
            return ApiResult.error("管理员密码修改失败！");
        }
    }
}
