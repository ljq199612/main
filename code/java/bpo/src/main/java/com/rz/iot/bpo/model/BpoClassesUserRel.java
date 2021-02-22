package com.rz.iot.bpo.model;

import java.util.Date;

public class BpoClassesUserRel {
    private Integer id;

    private Integer classGenerateId;

    private Integer classGroupId;

    private Date cycle;

    private Integer projectId;

    private String classSn;

    private Integer personId;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClassGenerateId() {
        return classGenerateId;
    }

    public void setClassGenerateId(Integer classGenerateId) {
        this.classGenerateId = classGenerateId;
    }

    public Integer getClassGroupId() {
        return classGroupId;
    }

    public void setClassGroupId(Integer classGroupId) {
        this.classGroupId = classGroupId;
    }

    public Date getCycle() {
        return cycle;
    }

    public void setCycle(Date cycle) {
        this.cycle = cycle;
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

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
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