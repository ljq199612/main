package com.rz.iot.bpo.model;

import java.util.Date;

public class BpoClassesGroupRel {
    private Integer id;

    private Integer projectId;

    private String classSn;

    private Integer workGroupId;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Integer classGenerateId;

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

    public String getClassSn() {
        return classSn;
    }

    public void setClassSn(String classSn) {
        this.classSn = classSn == null ? null : classSn.trim();
    }

    public Integer getWorkGroupId() {
        return workGroupId;
    }

    public void setWorkGroupId(Integer workGroupId) {
        this.workGroupId = workGroupId;
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

    public Integer getClassGenerateId() {
        return classGenerateId;
    }

    public void setClassGenerateId(Integer classGenerateId) {
        this.classGenerateId = classGenerateId;
    }
}