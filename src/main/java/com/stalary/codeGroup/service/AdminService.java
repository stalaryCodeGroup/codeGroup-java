package com.stalary.codeGroup.service;

import com.stalary.codeGroup.entity.Admin;
import com.stalary.codeGroup.repo.AdminRepo;
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

    public Admin findByAccount(String account) {
        return repo.findByAccount(account);//通过账号查找管理员
    }

    public List<Admin> sortByPosition() {
        return repo.sortByPosition();//通过职务排序
    }
}
