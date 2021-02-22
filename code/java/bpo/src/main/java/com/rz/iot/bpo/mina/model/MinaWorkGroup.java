package com.rz.iot.bpo.mina.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class MinaWorkGroup {
    //
    @ApiModelProperty(value = "工作小组id", example = "1")
    private Integer id;

    //
    private Integer sysWorkModuleRelId;

    //工作模块id
    @ApiModelProperty(value = "工作模块id", example = "1")
    private Integer workModelId;

    //工作小组编码
    @ApiModelProperty(value = "工作小组编码", example = "CJKS-SJD-SWC")
    private String groupCode;

    //工作小组名称
    @ApiModelProperty(value = "工作小组名称", example = "干活一组")
    private String groupName;

    //状态
    private Integer status;

    //小组人数
    private Integer groupNumber;

    //创建时间
    private Date createTime;

    //修改时间
    private Date updateTime;

    //1:早班2:晚班

    private Integer type;
}