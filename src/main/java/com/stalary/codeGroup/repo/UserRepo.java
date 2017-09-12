package com.stalary.codeGroup.repo;

import com.stalary.codeGroup.entity.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author:Stalary
 * @Description:用户数据库操作层
 * @Date Created in 2017/8/24
 */
public interface UserRepo extends BaseRepo<User,Integer>{

    @Query("select u from User u order by u.rank desc")
    List<User> sortByRank();//通过rank排序

    @Query("select u from User u order by u.registerTime asc")
    List<User> sortByRegisterTime();//通过注册日期排序

    @Query("select u from User u where u.studentNo = ?1")
    User findByStudentNo(String studentNo);//通过学号查找用户

    @Query("select u from User u where u.mail = ?1")
    User findByMail(String mail);//通过邮箱查找
}
