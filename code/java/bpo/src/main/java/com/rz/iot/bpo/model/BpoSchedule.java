package com.rz.iot.bpo.model;

import java.util.Date;

public class BpoSchedule {
    //
    private Integer id;

    //项目id
    private Integer projectId;

    //生成来源班次id
    private Integer fromClassId;

    //最近修改人
    private Integer userId;

    //需求人数
    private Integer peopleNumber;

    //是否是特殊岗位
    private Integer isSpecial;

    //特殊岗位补贴
    private String specialAllowance;

    //餐补
    private String mealAllowance;

    //晚班补贴
    private String nightAllowance;

    //单价
    private String unitPrice;

    //状态
    private Integer status;

    //创建时间
    private Date createTime;

    //修改时间
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getFromClassId() {
        return fromClassId;
    }

    public void setFromClassId(Integer fromClassId) {
        this.fromClassId = fromClassId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPeopleNumber() {
        return peopleNumber;
    }

    public void setPeopleNumber(Integer peopleNumber) {
        this.peopleNumber = peopleNumber;
    }

    public Integer getIsSpecial() {
        return isSpecial;
    }

    public void setIsSpecial(Integer isSpecial) {
        this.isSpecial = isSpecial;
    }

    public String getSpecialAllowance() {
        return specialAllowance;
    }

    public void setSpecialAllowance(String specialAllowance) {
        this.specialAllowance = specialAllowance == null ? null : specialAllowance.trim();
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

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice == null ? null : unitPrice.trim();
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