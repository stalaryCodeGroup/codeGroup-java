package com.stalary.codeGroup.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author:Stalary
 * @Description:积分
 * @Date Created in 2017/8/24
 */
@Entity
@Table(name = "rank")
public class Rank extends BaseObject{

    private Integer user_keyId;//关联的用户的keyId
    private Integer alterNumber;//积分修改数量
    private String alterDetail;//积分修改详情
    private Integer type;//积分变化类型 1 签到 2 参加活动 3 比赛 4 违规

    public Rank() {
    }

    public Rank(Integer keyId) {
        this.keyId = keyId;
    }

    public Integer getUser_keyId() {
        return user_keyId;
    }

    public void setUser_keyId(Integer user_keyId) {
        this.user_keyId = user_keyId;
    }

    public Integer getAlterNumber() {
        return alterNumber;
    }

    public void setAlterNumber(Integer alterNumber) {
        this.alterNumber = alterNumber;
    }

    public String getAlterDetail() {
        return alterDetail;
    }

    public void setAlterDetail(String alterDetail) {
        this.alterDetail = alterDetail;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
