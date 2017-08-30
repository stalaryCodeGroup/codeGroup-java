package com.stalary.codeGroup.repo;

import com.stalary.codeGroup.entity.Admin;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author:Stalary
 * @Description:管理员数据库操作层
 * @Date Created in 2017/8/24
 */
public interface AdminRepo extends BaseRepo<Admin, Integer>{

    @Query("select a from Admin a where a.account = ?1")
    Admin findByAccount(String account);//通过账号查找

    @Query("select a from Admin a where a.studentNo = ?1")
    Admin findByStudentNo(String studentNo);//通过学号查找

    @Query("select a from Admin a order by a.position asc")
    List<Admin> sortByPosition();//通过职务排序
}
