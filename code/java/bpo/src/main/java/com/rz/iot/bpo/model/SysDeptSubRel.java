package com.rz.iot.bpo.model;

public class SysDeptSubRel {
    private Integer id;

    private Integer subId;

    private Integer subType;

    private Integer deptId;

    private Integer status;

    public SysDeptSubRel(){}

    public SysDeptSubRel(Integer subId,Integer subType,Integer status){
        this.setSubId(subId);
        this.setSubType(subType);
        this.setStatus(status);
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
    }

    public Integer getSubType() {
        return subType;
    }

    public void setSubType(Integer subType) {
        this.subType = subType;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}