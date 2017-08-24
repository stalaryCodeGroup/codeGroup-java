package com.stalary.codeGroup.service;

import com.stalary.codeGroup.entity.Rank;
import com.stalary.codeGroup.entity.User;
import com.stalary.codeGroup.repo.RankRepo;
import com.stalary.codeGroup.util.WebUtils;
import com.stalary.codeGroup.viewmodel.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author:Stalary
 * @Description:积分业务层，处理用户的积分
 * @Date Created in 2017/8/24
 */
@Service
public class RankService extends BaseService<Rank,RankRepo>{

    @Resource
    private UserService userService;
    @Resource
    private LogService logService;

    @Autowired
    public RankService(RankRepo repo) {
        super(repo);
    }

    public ApiResult alterRank(Integer alterNumber, String alterDetail, Integer type) {
        Integer keyId = WebUtils.getLoginUserId();
        User user = userService.findOne(keyId);//通过keyId查找用户
        if(null == user) {
            return ApiResult.error("用户：" + keyId + "不存在");
        }
        Rank rank = new Rank();
        rank.setAlterNumber(alterNumber);//积分变化数量
        rank.setAlterDetail(alterDetail);//积分变化详情
        rank.setType(type);//积分变化类型 1 签到 2 参加活动 3 比赛 4 违规
        rank.setUser_keyId(keyId);//关联的用户keyId
        try {
            save(rank);
            logService.create("用户：" + keyId + "积分记录存储成功");
        } catch (Exception e) {
            logService.create("用户：" + keyId + "积分记录存储失败！");
        }
        Integer amount = user.getRank() != null ? user.getRank() : 1000;
        user.setRank(amount + alterNumber);//修改积分值
        try {
            userService.save(user);
            logService.create("用户：" + keyId + "积分修改成功");
            return ApiResult.ok("用户积分修改成功");
        } catch (Exception e) {
            logService.create("用户：" + keyId + "积分修改失败！");
            return ApiResult.error("用户积分修改失败！");
        }
    }
}
