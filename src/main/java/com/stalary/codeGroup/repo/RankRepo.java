package com.stalary.codeGroup.repo;

import com.stalary.codeGroup.entity.Rank;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author:Stalary
 * @Description:积分数据库操作层
 * @Date Created in 2017/8/24
 */
public interface RankRepo extends BaseRepo<Rank,Integer>{

    @Query("select r from Rank r where r.user_keyId = ?1 order by r.latestTime desc")
    List<Rank> findByUserKeyId(Integer keyId);//通过关联用户的keyId来查找积分纪录
}
