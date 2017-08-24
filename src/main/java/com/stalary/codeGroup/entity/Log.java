package com.stalary.codeGroup.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author:Stalary
 * @Description:日志
 * @Date Created in 2017/8/24
 */
@Entity
@Table(name = "log")
public class Log extends BaseObject{

    private String content;//日志内容

    public Log() {
    }

    public Log(Integer keyId) {
        this.keyId = keyId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
