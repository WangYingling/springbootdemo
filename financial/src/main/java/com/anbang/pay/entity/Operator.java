package com.anbang.pay.entity;

/**
 * 用户实体类
 * Created by Administrator on 2017/1/1.
 */
public class Operator {
    private Long id;
    private Long optimistic;
    private Long userName;
    private Long realName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOptimistic() {
        return optimistic;
    }

    public void setOptimistic(Long optimistic) {
        this.optimistic = optimistic;
    }

    public Long getUserName() {
        return userName;
    }

    public void setUserName(Long userName) {
        this.userName = userName;
    }

    public Long getRealName() {
        return realName;
    }

    public void setRealName(Long realName) {
        this.realName = realName;
    }
}
