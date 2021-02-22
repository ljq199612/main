package com.rz.iot.bpo.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class BpoAccountInfo {
    private Integer id;

    @ApiModelProperty(value = "银行卡姓名", example = "供应商")
    private String name;

    @ApiModelProperty(value = "银行卡卡号", example = "6666666666666666666")
    private String bankNumber;

    @ApiModelProperty(value = "开户行", example = "XX市XX区XX街道XX银行")
    private String bank;

    @ApiModelProperty(value = "组织Id", example = "4")
    private Integer companyId;

    @ApiModelProperty(value = "组织类型", example = "4")
    private Integer type;

    @ApiModelProperty(value = "状态", example = "1")
    private Byte status;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber == null ? null : bankNumber.trim();
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
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