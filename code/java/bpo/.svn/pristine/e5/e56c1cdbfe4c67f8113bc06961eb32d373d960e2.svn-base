package com.rz.iot.bpo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class SysCompany {
    @ApiModelProperty(value = "组织表Id", example = "3")
    private Integer id;

    @ApiModelProperty(value = "组织名称", example = "供应商A")
    private String companyName;

    @ApiModelProperty(value = "组织编号", example = "GYS-A")
    private String companyCode;

    @ApiModelProperty(value = "联系人", example = "联系人")
    private String contacts;

    @ApiModelProperty(value = "联系号码", example = "18888888888")
    private String phone;

    // 简称
    @ApiModelProperty(value = "简称", example = "xxx")
    private String nickName;

    @ApiModelProperty(value = "组织类型", example = "provider")
    private Integer orgType;

    @ApiModelProperty(value = "状态", example = "1")
    private Integer status;

    @ApiModelProperty(value = "创建时间", example = "2020-06-20 17:17:50")
    private Date createTime;

    @ApiModelProperty(value = "更新时间", example = "2020-06-20 17:17:50")
    private Date updateTime;

    public SysCompany(){}

    public SysCompany(Integer id, Integer status){
        this.setId(id);
        this.setStatus(status);
    }
}