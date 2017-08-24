package com.stalary.codeGroup.repo;

import com.stalary.codeGroup.entity.User;
import org.springframework.data.jpa.repository.Query;

/**
 * @Author:Stalary
 * @Description:用户数据库操作层
 * @Date Created in 2017/8/24
 */
public interface UserRepo extends BaseRepo<User,Integer>{

}
