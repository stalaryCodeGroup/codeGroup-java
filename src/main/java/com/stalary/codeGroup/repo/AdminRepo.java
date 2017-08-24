package com.stalary.codeGroup.repo;

import com.stalary.codeGroup.entity.Admin;
import org.springframework.data.jpa.repository.Query;

/**
 * @Author:Stalary
 * @Description:管理员数据库操作层
 * @Date Created in 2017/8/24
 */
public interface AdminRepo extends BaseRepo<Admin, Integer>{

    @Query("select a from Admin a where a.account = ?1")
    Admin findByAccount(String account);
}
