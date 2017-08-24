package com.stalary.codeGroup.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class BaseObject {

    public BaseObject() {
        this.latestTime = new Date();
    }

    public BaseObject(Integer keyId) {
        this.keyId = keyId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer keyId;


    /**
     * 最后修改时间
     */
    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    protected Date latestTime;


    public void setKeyId(Integer keyId) {
        this.keyId = keyId;
    }

    public Integer getKeyId() {
        return keyId;
    }

    public Date getLatestTime() {
        return latestTime;
    }

    public void setLatestTime(Date latestTime) {
        this.latestTime = latestTime;
    }
}
