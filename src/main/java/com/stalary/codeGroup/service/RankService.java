package com.stalary.codeGroup.service;

import com.stalary.codeGroup.entity.Rank;
import com.stalary.codeGroup.repo.RankRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:Stalary
 * @Description:积分业务层，处理用户的积分
 * @Date Created in 2017/8/24
 */
@Service
public class RankService extends BaseService<Rank,RankRepo>{

    @Autowired
    public RankService(RankRepo repo) {
        super(repo);
    }
}
