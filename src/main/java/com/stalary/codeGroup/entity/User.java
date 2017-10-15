package com.stalary.codeGroup.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author:Stalary
 * @Description:用户
 * @Date Created in 2017/8/24
 */
@Entity
@Table(name = "user")
public class User extends BaseObject{

    private String name;//姓名
    private String phone;//手机号
    private String password;//密码
    private String studentNo;//学号(账号)
    private String sex;//性别
    private Integer rank;//积分
    private String registerTime;//注册时间
    private String loginTime;//上次登陆时间
    private String region;//居住地
    private String major;//专业
    private String year;//年级
    private String mail;//邮箱
    private String QQ;//QQ号


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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }
}
