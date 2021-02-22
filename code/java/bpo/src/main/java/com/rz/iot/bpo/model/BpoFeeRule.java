package com.rz.iot.bpo.model;

import java.util.Date;

public class BpoFeeRule {
    //
    private Integer id;

    //计价规则名字
    private String ruleName;

    //优先级
    private Integer priorityLevel;

    //1:甲方给乙方2:乙方给供应商3:乙方给个人4:供应商给个人5:供应商给供应商
    private Integer type;

    //生效时间
    private Date effectiveTime;

    //失效时间
    private Date finishTime;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    //供应商信息表Id,当type为2时,标记是哪个供应商
    private Integer supplierInfoId;

    public Integer getSupplierInfoId() {
        return supplierInfoId;
    }

    public void setSupplierInfoId(Integer supplierInfoId) {
        this.supplierInfoId = supplierInfoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName == null ? null : ruleName.trim();
    }

    public Integer getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(Integer priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
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