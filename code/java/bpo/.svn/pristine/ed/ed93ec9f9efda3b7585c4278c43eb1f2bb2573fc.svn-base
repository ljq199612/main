package com.rz.iot.bpo.model.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 描述 : 项目生成工作小组参数
 * 作者 : wangluyao
 * 创建时间 : 2020/6/18 10:10
 * <p>
 * 修改原因 :
 * 修改人 :
 * 修改时间 ：
 */
@Data
public class BpoGroupParam {
    @ApiModelProperty(value = "系统配置工作小组id", example = "5")
    private Integer sysGroupId;
    @ApiModelProperty(value = "系统配置工作小组名称", example = "装车1组")
    private String sysGroupName;

    @ApiModelProperty(value = "系统配置工作小组编号", example = "GGMK-ZZZY-ZC")
    private String sysGroupCode;

    private Boolean codeExist;
    private Boolean codeInvalid;

    private Boolean nameExist;
    private List<BpoProcessParam> processes;
}
