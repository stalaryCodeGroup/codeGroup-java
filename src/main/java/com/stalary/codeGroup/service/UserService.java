package com.stalary.codeGroup.service;

import com.stalary.codeGroup.entity.User;
import com.stalary.codeGroup.repo.UserRepo;
import com.stalary.codeGroup.util.MD5Utils;
import com.stalary.codeGroup.viewmodel.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:Stalary
 * @Description:用户业务层，处理用户操作
 * @Date Created in 下午6:09 17/8/24
 */
@Service
public class UserService extends BaseService<User, UserRepo> {

    @Resource
    private LogService logService;

    @Autowired
    public UserService(UserRepo repo) {
        super(repo);
    }

    public List<User> sortByRank() {
        return repo.sortByRank();//通过rank排序
    }

    public List<User> sortByRegisterTime() {
        return repo.sortByRegisterTime();//通过注册日期排序
    }

    public User findByMail(String mail) {
        return repo.findByMail(mail);//通过邮箱查找
    }

    public ApiResult alterPassword(User user, String password) {
        user.setPassword(MD5Utils.MD5(password));
        try {
            save(user);
            return ApiResult.ok("用户密码修改成功");
        } catch (Exception e) {
            return ApiResult.error("用户密码修改失败！");
        }
    }

    public User findByStudentNo(String studentNo) {
        return repo.findByStudentNo(studentNo);//通过学号查找用户
    }
}
