package com.rz.iot.bpo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BpoProcess {
    //
    @ApiModelProperty(value = "工序id", example = "1")
    private Integer id;

    //
    private Integer sysWorkModuleRelId;

    //工作小组id
    @ApiModelProperty(value = "工作小组id", example = "1")
    private Integer workGroupId;

    //排序优先级
    private Integer sort;

    //是否有产品,1:有,0:没有
    private Byte isProduct;

    //工序名称
    @ApiModelProperty(value = "工序名称", example = "干这个")
    private String processName;

    //工序编号
    @ApiModelProperty(value = "工序名称", example = "GFC-GZG")
    private String processCode;

    //状态
    private Integer status;

    //创建时间
    private Date createTime;

    //修改时间
    private Date updateTime;
}