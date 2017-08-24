package com.stalary.codeGroup.service;

import com.stalary.codeGroup.entity.Admin;
import com.stalary.codeGroup.repo.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
