package com.rz.iot.bpo.model;

import java.util.Date;

public class BpoPersonWage {
    private Integer id;

    private Integer wageType;

    private Integer payType;

    private String wage;

    private Integer payChannels;

    private String mealAllowance;

    private String nightAllowance;

    private String otherAllowance;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWageType() {
        return wageType;
    }

    public void setWageType(Integer wageType) {
        this.wageType = wageType;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getWage() {
        return wage;
    }

    public void setWage(String wage) {
        this.wage = wage == null ? null : wage.trim();
    }

    public Integer getPayChannels() {
        return payChannels;
    }

    public void setPayChannels(Integer payChannels) {
        this.payChannels = payChannels;
    }

    public String getMealAllowance() {
        return mealAllowance;
    }

    public void setMealAllowance(String mealAllowance) {
        this.mealAllowance = mealAllowance == null ? null : mealAllowance.trim();
    }

    public String getNightAllowance() {
        return nightAllowance;
    }

    public void setNightAllowance(String nightAllowance) {
        this.nightAllowance = nightAllowance == null ? null : nightAllowance.trim();
    }

    public String getOtherAllowance() {
        return otherAllowance;
    }

    public void setOtherAllowance(String otherAllowance) {
        this.otherAllowance = otherAllowance == null ? null : otherAllowance.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}