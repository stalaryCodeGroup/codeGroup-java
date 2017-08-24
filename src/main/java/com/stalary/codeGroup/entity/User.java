package com.stalary.codeGroup.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author:Stalary
 * @Description:用户
 * @Date Created in 2017/8/24
 */
@Entity
@Table(name = "user")
public class User extends BaseObject{

    private String name;//姓名
    private String account;//账号
    private String password;//密码
    private String studentNo;//学号
    private String sex;//性别
    private Integer rank;//积分
    private Date loginTime;//登陆时间

    public User() {
    }

    public User(Integer keyId) {
         this.keyId = keyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
}