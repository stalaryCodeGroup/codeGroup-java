package com.stalary.codeGroup.controller;

import com.stalary.codeGroup.service.RankService;
import com.stalary.codeGroup.viewmodel.ApiResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author:Stalary
 * @Description:
 * @Date Created in 2017/8/24
 */
@RestController
@RequestMapping(value = "rank")
public class RankController {

    @Resource
    private RankService rankService;

    @ApiOperation(value = "修改用户积分时调用，需要传入修改数量，修改详情（可选），修改类型 1 签到 2 参加活动 3 比赛 4 违规--（前台）--（后台）")
    @RequestMapping(value = "/alterRank",method = RequestMethod.POST)
    public ApiResult alterRank(Integer alterNumber, String alterDetail, Integer type) {
        return rankService.alterRank(alterNumber, alterDetail, type);
    }
}
