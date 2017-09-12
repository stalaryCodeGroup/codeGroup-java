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

    @Query("select a from Admin a where a.phone = ?1")
    Admin findByPhone(String account);//通过手机号查找

    @Query("select a from Admin a where a.studentNo = ?1")
    Admin findByStudentNo(String studentNo);//通过学号查找

    @Query("select a from Admin a order by a.position asc, a.year asc")
    List<Admin> sortByPositionAndYear();//通过职务和年级排序

    @Query("select a from Admin a where a.mail = ?1")
    Admin findByMail(String mail);//通过邮箱查找
}
