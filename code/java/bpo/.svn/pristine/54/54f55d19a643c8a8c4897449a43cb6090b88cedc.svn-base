package com.rz.iot.bpo.model;

import io.swagger.models.auth.In;

import java.util.Date;

public class BpoSupplierInfo {
    private Integer id;

    private Integer orgId;

    private Integer projectId;

    private Integer contractId;

    private Integer parentId;

    private Byte status;

    private Byte isAgencySalary;

    private Date createTime;

    private Date updateTime;

    public BpoSupplierInfo(){}

    public BpoSupplierInfo(Integer orgId, Integer status){
        this.setOrgId(orgId);
        this.setStatus(status.byteValue());
    }

    //公司成立时间
    private Date companyCreateTime;

    //公司规模 (存储业务字典key)
    private String companySize;

    //所属行业(存储业务字典key)
    private String industry;

    //营业执照(ftp地址链接)
    private Integer companyFileId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getIsAgencySalary() {
        return isAgencySalary;
    }

    public void setIsAgencySalary(Byte isAgencySalary) {
        this.isAgencySalary = isAgencySalary;
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

    public Date getCompanyCreateTime() {
        return companyCreateTime;
    }

    public void setCompanyCreateTime(Date companyCreateTime) {
        this.companyCreateTime = companyCreateTime;
    }

    public String getCompanySize() {
        return companySize;
    }

    public void setCompanySize(String companySize) {
        this.companySize = companySize;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public Integer getCompanyFileId() {
        return companyFileId;
    }

    public void setCompanyFileId(Integer companyFileId) {
        this.companyFileId = companyFileId;
    }
}