package com.stalary.codeGroup.service;

import com.stalary.codeGroup.entity.User;
import com.stalary.codeGroup.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:Stalary
 * @Description:用户业务层，处理用户操作
 * @Date Created in 下午6:09 17/8/24
 */
@Service
public class UserService extends BaseService<User, UserRepo> {
    @Autowired
    protected UserService(UserRepo repo) {
        super(repo);
    }


}
